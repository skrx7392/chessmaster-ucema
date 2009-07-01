namespace WpfApplication1
{
    using System;
    using System.Globalization;
    using System.Windows;
    using System.Windows.Controls;
    using System.Windows.Media;
    using System.Windows.Media.Animation;
    using System.Windows.Shapes;
    using Models;

    /// <summary>
    /// Interaction logic for ChessBoard.xaml
    /// </summary>
    public partial class ChessBoard : Window
    {
        private Point selectedPosition;
        private Rectangle selectedPiece;
        private Rectangle[] visualPieces;

        private const int SquareSize = 70;

        /// <summary>
        /// Random generator used for generating unique names for pieces.
        /// </summary>
        private static readonly Random random = new Random(unchecked((int)DateTime.Now.Ticks));

        /// <summary>
        /// The move storyboard.
        /// </summary>
        private Storyboard moveStoryboard;

        public ChessBoard()
        {
            this.InitializeComponent();

            this.selectedPosition = new Point(-1, -1);
            this.visualPieces = new Rectangle[Board.SquareNo];
        }

        public ChessBoardPresentationModel Model
        {
            get { return this.DataContext as ChessBoardPresentationModel; }
            set { this.DataContext = value; }
        }

        public void DrawBoard(Board board)
        {
            foreach (UIElement uiElement in this.BoardGrid.Children)
            {
                Canvas canvas = uiElement as Canvas;

                if (canvas != null)
                {
                    canvas.Children.Clear();
                }
            }

            for (int i = 0; i < Board.SquareNo; i++)
            {
                if ((this.visualPieces[i] != null || board[i] != null) &&
                    (this.visualPieces[i] == null || board[i] == null ||
                     !this.visualPieces[i].Name.StartsWith(String.Concat(board[i].Color.ToString(), board[i].GetType().Name))))
                {
                    // remove the old visual representation
                    if (this.visualPieces[i] != null)
                    {
                        // pieceLayer.Children.Remove(visualPieces[sqIndex]);
                        this.visualPieces[i] = null;
                    }

                    // add the new visual piece
                    if (board[i] != null)
                    {
                        string pieceName = String.Concat(board[i].Color.ToString(), board[i].GetType().Name);
                        this.visualPieces[i] = this.GenerateVisualPiece(pieceName);
                    }
                }

                // set the visual piece position
                if (this.visualPieces[i] != null)
                {
                    Canvas canvas = this.BoardGrid.Children[i + 1] as Canvas;
                    canvas.Children.Add(this.visualPieces[i]);
                    //SetSpritePosition(visualPieces[i], i);
                }
                else
                {
                    // remove all pieces
                    for (int j = 0; j < Board.SquareNo; j++)
                    {
                        if (this.visualPieces[j] != null)
                        {
                            // pieceLayer.Children.Remove(visualPieces[sqIndex]);
                            this.visualPieces[j] = null;
                        }
                    }
                }
            }
        }

        private Rectangle GenerateVisualPiece(string pieceName)
        {
            Rectangle visualPiece = new Rectangle();

            // set the dimension
            visualPiece.Width = SquareSize;
            visualPiece.Height = SquareSize;

            // set the type
            visualPiece.Fill = FindResource(pieceName + "Brush") as Brush;

            // set the unique name as the piece type name plus a random generated number 
            visualPiece.Name = pieceName + random.Next(100000000, 1000000000).ToString(CultureInfo.InvariantCulture);

            visualPiece.RenderTransform = new TranslateTransform();

            // add it to the piece layer and register its name
            // pieceLayer.Children.Add(visualPiece);
            this.BoardGrid.RegisterName(visualPiece.Name, visualPiece);

            return visualPiece;
        }

        private void Rectangle_MouseLeftButtonDown(object sender, System.Windows.Input.MouseButtonEventArgs e)
        {
            Canvas canvas = sender as Canvas;

            if (canvas != null)
            {
                int column = Grid.GetColumn(canvas);
                int row = Grid.GetRow(canvas);

                if (this.selectedPosition.X != -1 && this.selectedPosition.Y != -1 && this.selectedPiece != null)
                {
                    int index = (int) this.selectedPosition.X + ((int) this.selectedPosition.Y * 8) + 1;

                    this.Model.Move((int)this.selectedPosition.Y, (int)this.selectedPosition.X, column, row);

                    //Canvas oldCanvas = this.BoardGrid.Children[index] as Canvas;

                    //if (oldCanvas != null && oldCanvas.Children.Count > 0)
                    //{
                    //    Rectangle element = oldCanvas.Children[0] as Rectangle;

                    //    if (element != null)
                    //    {
                    //        this.Animate(canvas, oldCanvas, element);
                    //    }
                    //}

                    this.selectedPosition.X = -1;
                    this.selectedPosition.Y = -1;
                    this.SelectionBox.Visibility = Visibility.Collapsed;
                    this.selectedPiece = null;
                }
                else
                {
                    Grid.SetColumn(this.SelectionBox, column);
                    Grid.SetRow(this.SelectionBox, row);
                    this.selectedPosition.X = row;
                    this.selectedPosition.Y = column;
                    this.SelectionBox.Visibility = Visibility.Visible;

                    if (canvas.Children.Count > 0)
                    {
                        this.selectedPiece = canvas.Children[0] as Rectangle;
                    }
                }
            }
        }

        private bool IsChessPieceAtPosition(Point point)
        {
            return false;
        }

        private void Animate(Canvas canvas, Canvas c, Rectangle element)
        {
            // set a reference to the animated move 
            // animatedMove = move;

            // bring the animated piece to front
            // Canvas.SetZIndex(visualPieces[move.From], Canvas.GetZIndex(visualPieces[move.From]) + 1);

            // set the piece translation duration according to the animation parameters
            int translationDuration = 300;

            // set the captured piece fading duration to be less than the translation duration
            int fadeDuration = translationDuration - 100;

            // set the move storyboard
            this.moveStoryboard = new Storyboard();
            this.moveStoryboard.FillBehavior = FillBehavior.Stop;

            // set the translation animation for this move
            this.SetTranslationAnimation(this.moveStoryboard, element, translationDuration);

            // if (move is CastlingMove)
            // {
            //    // set the translation animation for the rook move
            // SetTranslationAnimation(moveStoryboard, (move as CastlingMove).RookMove, translationDuration);
            // }
            if (canvas.Children.Count > 0)
            {
                // set the fading animation for the captured piece
                this.SetOpacityAnimation(this.moveStoryboard, canvas.Children[0] as Rectangle, fadeDuration);
            }

            // detect when the animation has terminated
            this.moveStoryboard.Completed += delegate(object sender, EventArgs e)
            {
                // send the animated piece to back
                // Canvas.SetZIndex(visualPieces[move.From], Canvas.GetZIndex(visualPieces[move.From]) - 1);
                c.Children.Clear();
                canvas.Children.Clear();
                canvas.Children.Add(element);
            };

            // the storyboard will not be modified so it will be freezed
            this.moveStoryboard.Freeze();

            // start the animation
            this.moveStoryboard.Begin(this.BoardGrid, HandoffBehavior.Compose);
        }

        /// <summary>
        /// Sets the fading animation for the captured piece.
        /// </summary>
        /// <param name="moveStoryboard"></param>
        /// <param name="element"></param>
        /// <param name="duration"></param>
        private void SetOpacityAnimation(Storyboard moveStoryboard, Rectangle element, int duration)
        {
            // animate the captured piece opacity from the current value (1.0) to 0.0
            // the animations will not be modified so they will be freezed

            // set the opacity animation
            DoubleAnimation opacityAnimation = new DoubleAnimation();
            opacityAnimation.To = 0.0;
            opacityAnimation.Duration = new Duration(TimeSpan.FromMilliseconds(duration));
            opacityAnimation.BeginTime = TimeSpan.FromMilliseconds(500);
            Storyboard.SetTargetName(opacityAnimation, element.Name);
            Storyboard.SetTargetProperty(opacityAnimation, new PropertyPath(Rectangle.OpacityProperty));
            opacityAnimation.Freeze();

            // add the animation to the move storyboard
            moveStoryboard.Children.Add(opacityAnimation);
        }

        /// <summary>
        /// Sets the translation animation for the move.
        /// </summary>
        /// <param name="moveStoryboard"></param>
        /// <param name="move"></param>
        /// <param name="duration"></param>
        private void SetTranslationAnimation(Storyboard moveStoryboard, Rectangle element, int duration)
        {
            // animate the piece from the current position to the ending square
            // the animations will not be modified so they will be freezed

            // set the animation along the X axis
            DoubleAnimation xAnimation = new DoubleAnimation();
            xAnimation.To = 100;
            xAnimation.Duration = new Duration(TimeSpan.FromMilliseconds(duration));
            xAnimation.BeginTime = TimeSpan.FromMilliseconds(500);
            Storyboard.SetTargetName(xAnimation, element.Name);
            Storyboard.SetTargetProperty(xAnimation, new PropertyPath("(0).(1)", RenderTransformProperty, TranslateTransform.XProperty));
            xAnimation.Freeze();

            // set the animation along the Y axis
            DoubleAnimation yAnimation = new DoubleAnimation();
            yAnimation.To = 100;
            yAnimation.Duration = new Duration(TimeSpan.FromMilliseconds(duration));
            yAnimation.BeginTime = TimeSpan.FromMilliseconds(500);
            Storyboard.SetTargetName(yAnimation, element.Name);
            Storyboard.SetTargetProperty(yAnimation, new PropertyPath("(0).(1)", RenderTransformProperty, TranslateTransform.YProperty));
            yAnimation.Freeze();

            // add the animations to the storyboard
            moveStoryboard.Children.Add(xAnimation);
            moveStoryboard.Children.Add(yAnimation);
        }

        private void Start_Click(object sender, RoutedEventArgs e)
        {
            this.Model.Reset();
        }
    }
}