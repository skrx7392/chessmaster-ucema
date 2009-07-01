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

        public Board Board { get; private set; }
    }
}