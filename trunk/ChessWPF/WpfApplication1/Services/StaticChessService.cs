namespace WpfApplication1.Services
{
    using System;
    using System.Collections.Generic;
    using System.Configuration;
    using System.Globalization;
    using System.IO;
    using System.Net;
    using System.Xml.Linq;
    using Models;

    public class StaticChessService : IChessService
    {
        private const string UriPattern = "http://{0}:8080/chessmaster-web/ChessPublisherServlet?service={1}";
        private const string HumanMovePattern = "&from={0}&to={1}";
        private const string IPConfigurationKey = "IPMati";
        private string ip;

        public StaticChessService()
        {
            this.ip = ConfigurationManager.AppSettings[IPConfigurationKey];
        }

        public event EventHandler<BoardEventArgs> MoveCompleted;

        public event EventHandler<BoardEventArgs> ResetCompleted;

        public void MoveAsync(string currentPosition, string newPosition)
        {
            WebClient client = new WebClient();

            client.DownloadStringCompleted += (sender, e) =>
            {
                if (!e.Cancelled)
                {
                    if (e.Error == null)
                    {
                        StringReader reader = new StringReader(e.Result);
                        XDocument document = XDocument.Load(reader);
                        Board board = this.ParseBoard(document);

                        this.OnMoveCompleted(board);
                    }
                    else
                    {
                        this.OnMoveCompleted(e.Error);
                    }
                }
            };

            string uriString = String.Format(CultureInfo.InvariantCulture, UriPattern, this.ip, "play");
            
            if (!String.IsNullOrEmpty(currentPosition) && !String.IsNullOrEmpty(newPosition))
            {
                uriString = String.Concat(uriString, string.Format(HumanMovePattern, currentPosition, newPosition));
            }

            Uri uri = new Uri(uriString);
            client.DownloadStringAsync(uri);
        }

        public void ResetAsync()
        {
            WebClient client = new WebClient();

            client.DownloadStringCompleted += (sender, e) =>
            {
                if (e.Error == null && !e.Cancelled)
                {
                    StringReader reader = new StringReader(e.Result);
                    XDocument document = XDocument.Load(reader);
                    Board board = this.ParseBoard(document);

                    this.OnResetCompleted(board);
                }
            };

            string uriString = String.Format(CultureInfo.InvariantCulture, UriPattern, this.ip, "reset");
            
            Uri uri = new Uri(uriString);
            client.DownloadStringAsync(uri);
        }

        public Board GetStartingBoard()
        {
            Board board = new Board();
            board.GenerateStartingBoard();

            return board;
        }

        private static ChessPiece TypeToPiece(string type, Color color)
        {
            switch (type.ToUpper())
            {
                case "PAWN":
                    return new Pawn(color);
                case "QUEEN":
                    return new Queen(color);
                case "KING":
                    return new King(color);
                case "BISHOP":
                    return new Bishop(color);
                case "KNIGHT":
                    return new Knight(color);
                case "ROOK":
                    return new Rook(color);
                default:
                    throw new ArgumentOutOfRangeException(type);
            }
        }

        private Board ParseBoard(XDocument document)
        {
            Board board = new Board();
            
            IEnumerable<XElement> chessPieces = document.Descendants("chessPiece");

            foreach (XElement element in chessPieces)
            {
                XAttribute type = element.Attribute("type");
                XAttribute color = element.Attribute("color");
                XAttribute column = element.Attribute("column");
                XAttribute row = element.Attribute("row");

                if (type != null && color != null && column != null && row != null)
                {
                    Color pieceColor = (Color)Enum.Parse(typeof(Color), color.Value, true);
                    string letter = column.Value;
                    int rowNumber = int.Parse(row.Value);

                    ChessPiece piece = TypeToPiece(type.Value, pieceColor);

                    int index = board.GetIndexByPosition(String.Concat(letter, rowNumber));
                    board[index] = piece;
                }
            }

            return board;
        }

        private void OnMoveCompleted(Board board)
        {
            EventHandler<BoardEventArgs> completed = this.MoveCompleted;
            if (completed != null)
            {
                completed(this, new BoardEventArgs(board));
            }
        }

        private void OnResetCompleted(Board board)
        {
            EventHandler<BoardEventArgs> completed = this.ResetCompleted;
            if (completed != null)
            {
                completed(this, new BoardEventArgs(board));
            }
        }

        private void OnMoveCompleted(Exception exception)
        {
            EventHandler<BoardEventArgs> completed = this.MoveCompleted;
            if (completed != null)
            {
                completed(this, new BoardEventArgs(exception));
            }
        }

        private void OnResetCompleted(Exception exception)
        {
            EventHandler<BoardEventArgs> completed = this.ResetCompleted;
            if (completed != null)
            {
                completed(this, new BoardEventArgs(exception));
            }
        }
    }
}