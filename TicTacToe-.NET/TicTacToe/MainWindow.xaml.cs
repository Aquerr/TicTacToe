using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace TicTacToe
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        string currentPlayer = "X";
        bool gameEnded = false;
        int[][] expectedResults;
        Dictionary<int, Button> fieldDict;

        public MainWindow()
        {
            InitializeComponent();
        }

        private void Window_Loaded(object sender, RoutedEventArgs e)
        {
            expectedResults = new int[][]
            {
                new int[]{1,2,3},
                new int[]{1,4,7},
                new int[]{2,5,8},
                new int[]{4,5,6},
                new int[]{3,6,9},
                new int[]{7,8,9},
                new int[]{1,5,9},
                new int[]{3,5,7}
            };

            fieldDict = new Dictionary<int, Button>();
            fieldDict.Add(1, btn1);
            fieldDict.Add(2, btn2);
            fieldDict.Add(3, btn3);
            fieldDict.Add(4, btn4);
            fieldDict.Add(5, btn5);
            fieldDict.Add(6, btn6);
            fieldDict.Add(7, btn7);
            fieldDict.Add(8, btn8);
            fieldDict.Add(9, btn9);
        }

        private void Btn_Click(object sender, RoutedEventArgs e)
        {
            if (!gameEnded)
            {
                if (sender is Button)
                {
                    Button clickedButton = (Button)sender;

                    if (clickedButton.Content == null || clickedButton.Content.Equals(""))
                    {
                        clickedButton.Content = currentPlayer;
                    }

                    if (DidWin(currentPlayer))
                    {
                        MessageBox.Show("Congratulations! You won!", "Kółko i krzyżyk");
                        gameEnded = true;
                    }

                    if (!MovementsExist())
                    {
                        MessageBox.Show("Draw! Restart your game to play again!", "Kółko i krzyżyk");
                    }

                    SwitchPlayer();
                }
            }
            else
            {
                MessageBox.Show("Game is ended! Restart the game to play again.", "Kółko i krzyżyk");
            }
        }

        private bool MovementsExist()
        {
            foreach (var field in fieldDict.Values)
            {
                if(field.Content == null || field.Content.Equals(""))
                {
                    return true;
                }
            }

            return false;
        }

        private bool DidWin(string currentPlayer)
        {
            for (int i = 0; i < expectedResults.Length; i++)
            {
                int[] expectedResult = expectedResults[i];

                Button field1 = null;
                Button field2 = null;
                Button field3 = null;

                fieldDict.TryGetValue(expectedResult[0], out field1);
                fieldDict.TryGetValue(expectedResult[1], out field2);
                fieldDict.TryGetValue(expectedResult[2], out field3);

                if(field1 != null && field2 != null && field3 != null)
                {
                    if (field1.Content != null && field2.Content != null && field3.Content != null)
                    {
                        if (field1.Content.Equals(currentPlayer) && field2.Content.Equals(currentPlayer) && field3.Content.Equals(currentPlayer))
                        {
                            return true;
                        }
                    }
                }
            }

            return false;
        }

        private void SwitchPlayer()
        {
            currentPlayer = currentPlayer.Equals("X") ? "O" : "X";
        }
    }
}
