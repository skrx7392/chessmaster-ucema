namespace WpfApplication1.Services
{
    using System;
    using Models;

    public class BoardEventArgs : EventArgs
    {
        public BoardEventArgs(Board board)
        {
            this.Board = board;
        }

        public BoardEventArgs(Exception exception)
        {
            this.Error = exception;
        }

        public Board Board { get; private set; }

        public Exception Error { get; private set; }
    }
}