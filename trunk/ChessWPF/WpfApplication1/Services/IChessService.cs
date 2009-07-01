namespace WpfApplication1.Services
{
    using System;
    using Models;

    public interface IChessService
    {
        event EventHandler<BoardEventArgs> MoveCompleted;

        event EventHandler<BoardEventArgs> ResetCompleted;

        void MoveAsync(string currentPosition, string newPosition);

        void ResetAsync();

        Board GetStartingBoard();
    }
}