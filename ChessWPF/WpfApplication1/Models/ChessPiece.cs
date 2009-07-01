namespace WpfApplication1.Models
{
    public class ChessPiece
    {
        public ChessPiece() : this(Color.White)
        {
        }

        public ChessPiece(Color color)
        {
            Color = color;
        }

        public Color Color { get; private set; }
    }
}
