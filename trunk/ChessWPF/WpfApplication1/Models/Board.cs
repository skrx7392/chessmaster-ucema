namespace WpfApplication1.Models
{
    using System;
    using System.Collections.Generic;

    public class Board
    {
        /// <summary>
        /// The number of pieces on the board side.
        /// </summary>
        public const int SideSquareNo = 8;

        /// <summary>
        /// The number of pieces.
        /// </summary>
        public const int SquareNo = SideSquareNo * SideSquareNo;

        /// <summary>
        /// A1 square position.
        /// </summary>
        public const int A1 = 7;

        /// <summary>
        /// B1 square position.
        /// </summary>
        public const int B1 = 15;

        /// <summary>
        /// C1 square position.
        /// </summary>
        public const int C1 = 23;

        /// <summary>
        /// D1 square position.
        /// </summary>
        public const int D1 = 31;

        /// <summary>
        /// E1 square position.
        /// </summary>
        public const int E1 = 39;

        /// <summary>
        /// F1 square position.
        /// </summary>
        public const int F1 = 47;

        /// <summary>
        /// G1 square position.
        /// </summary>
        public const int G1 = 55;

        /// <summary>
        /// H1 square position.
        /// </summary>
        public const int H1 = 63;

        /// <summary>
        /// A8 square position.
        /// </summary>
        public const int A8 = 0;

        /// <summary>
        /// B8 square position.
        /// 
        /// </summary>
        public const int B8 = 8;

        /// <summary>
        /// C8 square position.
        /// </summary>
        public const int C8 = 16;

        /// <summary>
        /// D8 square position.
        /// </summary>
        public const int D8 = 24;

        /// <summary>
        /// E8 square position.
        /// </summary>
        public const int E8 = 32;

        /// <summary>
        /// F8 square position.
        /// </summary>
        public const int F8 = 40;

        /// <summary>
        /// G8 square position.
        /// </summary>
        public const int G8 = 48;

        /// <summary>
        /// H8 square position.
        /// </summary>
        public const int H8 = 56;

        /// <summary>
        /// Array of 64 pieces, from left to right, top to bottom.
        /// </summary>
        private ChessPiece[] pieces;

        private Dictionary<string, int> board = new Dictionary<string, int>();

        public Board()
        {
            this.pieces = new ChessPiece[SquareNo];

            string[] letters = { "A", "B", "C", "D", "E", "F", "G", "H" };

            int j = 0;

            foreach (string letter in letters)
            {
                for (int i = 8; i > 0; i--)
                {
                    this.board.Add(letter + i, j);
                    j++;
                }
            }
        }

        public void GenerateStartingBoard()
        {
            this.pieces[A8] = new Rook(Color.Black);
            this.pieces[B8] = new Knight(Color.Black);
            this.pieces[C8] = new Bishop(Color.Black);
            this.pieces[D8] = new Queen(Color.Black);
            this.pieces[E8] = new King(Color.Black);
            this.pieces[F8] = new Bishop(Color.Black);
            this.pieces[G8] = new Knight(Color.Black);
            this.pieces[H8] = new Rook(Color.Black);

            for (int i = 1; i < SideSquareNo * SideSquareNo; i = i + 8)
            {
                this.pieces[i] = new Pawn(Color.Black);
            }

            for (int i = SideSquareNo - 2; i < SideSquareNo * SideSquareNo; i = i + 8)
            {
                this.pieces[i] = new Pawn(Color.White);
            }

            this.pieces[A1] = new Rook(Color.White);
            this.pieces[B1] = new Knight(Color.White);
            this.pieces[C1] = new Bishop(Color.White);
            this.pieces[D1] = new Queen(Color.White);
            this.pieces[E1] = new King(Color.White);
            this.pieces[F1] = new Bishop(Color.White);
            this.pieces[G1] = new Knight(Color.White);
            this.pieces[H1] = new Rook(Color.White);
        }

        /// <summary>
        /// Indexer for the pieces.
        /// </summary>
        public ChessPiece this[int index]
        {
            get { return this.pieces[index]; }
            internal set { this.pieces[index] = value; }
        }

        public int GetIndexByPosition(string position)
        {
            if (this.board.ContainsKey(position))
            {
                return this.board[position];
            }

            throw new ArgumentOutOfRangeException(position);
        }
    }

}
