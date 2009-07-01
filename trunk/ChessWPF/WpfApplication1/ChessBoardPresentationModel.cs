namespace WpfApplication1
{
    using System;
    using Services;

    public class ChessBoardPresentationModel
    {
        private readonly IChessService chessService;
        private bool lastMoveWasMadeByHuman;

        public ChessBoardPresentationModel(ChessBoard chessboard, IChessService chessService)
        {
            this.chessService = chessService;
            this.chessService.MoveCompleted += this.ChessService_MoveCompleted;
            this.chessService.ResetCompleted += (sender, e) =>
                                                    {
                                                        if (e.Error == null)
                                                        {
                                                            this.View.DrawBoard(e.Board);
                                                        }
                                                    };

            this.View = chessboard;

            this.View.Model = this;
            this.Reset();
        }

        public ChessBoard View { get; private set; }

        public void Reset()
        {
            this.chessService.ResetAsync();
        }

        public void Move(int actualColumn, int actualRow, int newColumn, int newRow)
        {
            if (!this.lastMoveWasMadeByHuman)
            {
                string actualPosition = String.Concat(ColumnToLetter(actualColumn), Math.Abs(8 - actualRow));
                string newPosition = String.Concat(ColumnToLetter(newColumn), Math.Abs(8 - newRow));
                if (!actualPosition.Equals(newPosition))
                {
                    this.lastMoveWasMadeByHuman = true;
                    this.chessService.MoveAsync(actualPosition, newPosition);
                }
            }
            else
            {
                this.chessService.MoveAsync(null, null);
            }
        }

        private static string ColumnToLetter(int column)
        {
            switch (column)
            {
                case 0:
                    return "A";
                case 1:
                    return "B";
                case 2:
                    return "C";
                case 3:
                    return "D";
                case 4:
                    return "E";
                case 5:
                    return "F";
                case 6:
                    return "G";
                case 7:
                    return "H";
                default:
                    throw new ArgumentOutOfRangeException(column.ToString());
            }
        }

        private void ChessService_MoveCompleted(object sender, BoardEventArgs e)
        {
            if (e.Error == null)
            {
                this.View.DrawBoard(e.Board);

                if (this.lastMoveWasMadeByHuman)
                {
                    this.lastMoveWasMadeByHuman = false;
                    this.chessService.MoveAsync(null, null);
                }
            }
        }
    }
}