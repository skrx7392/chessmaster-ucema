namespace WpfApplication1
{
    using System.Windows;
    using Services;

    public partial class App : Application
	{
        private void Application_Startup(object sender, StartupEventArgs e)
        {
            ChessBoard board = new ChessBoard();
            IChessService chessService = new StaticChessService();
            ChessBoardPresentationModel model = new ChessBoardPresentationModel(board, chessService);

            this.MainWindow = model.View;
            this.MainWindow.Show();
        }
    }
}