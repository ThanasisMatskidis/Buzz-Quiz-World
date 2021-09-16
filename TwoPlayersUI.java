import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author Thanasis, Aris
 */
public class TwoPlayersUI {

    private JFrame gameFrame;
    private JPanel gamePanel;
    private JLabel startLabel;
    private JLabel player1NameLabel;
    private JLabel player2NameLabel;
    private JLabel gameModeLabel;
    private JLabel roundLabel;
    private JLabel categoryLabel;
    private JLabel imageLabel;
    private JLabel questionLabel;
    private JLabel player1Label;
    private JLabel player2Label;
    private JLabel player1ResultLabel;
    private JLabel player2ResultLabel;
    private JLabel player1InstructionsLabel;
    private JLabel player2InstructionsLabel;
    private JRadioButton ans11;
    private JRadioButton ans12;
    private JRadioButton ans13;
    private JRadioButton ans14;
    private JRadioButton ans21;
    private JRadioButton ans22;
    private JRadioButton ans23;
    private JRadioButton ans24;
    private ButtonGroup player1AnswersGroup;
    private ButtonGroup player2AnswersGroup;
    private JTextField player1NameText;
    private JTextField player2NameText;
    private JButton goButton;
    private JButton nextQuestionButton;
    private JButton correctAnswerButton;
    private JButton betButton;
    private JButton stopTheClockButton;
    private JButton quickAnswerButton;
    private JButton thermometerButton;
    private Player player1;
    private Player player2;
    private ArrayList<Category> categories;
    private Question question;
    private int answeredQuestions;
    private int round;
    private boolean player1Answered;
    private boolean player2Answered;
    private Instant start;
    private Instant player1Finish;
    private Instant player2Finish;
    private int player1CorrectAnswers;
    private int player2CorrectAnswers;

    public TwoPlayersUI(ArrayList<Category> categories) {
        gameFrame = new JFrame("Buzz quiz!");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setSize(600, 500);
        gameFrame.setFocusable(true);

        gamePanel = new JPanel();
        gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.Y_AXIS));
        gameFrame.add(gamePanel);

        startLabel = new JLabel("Hello, let's play Buzz!");
        startLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        gamePanel.add(startLabel);

        player1NameLabel = new JLabel("Payer 1 enter your nickname: ");
        player1NameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        gamePanel.add(player1NameLabel);

        player1NameText = new JTextField(30);
        player1NameText.setMaximumSize(player1NameText.getPreferredSize());
        player1NameText.setAlignmentX(Component.CENTER_ALIGNMENT);
        gamePanel.add(player1NameText);

        player2NameLabel = new JLabel("Payer 2 enter your nickname: ");
        player2NameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        gamePanel.add(player2NameLabel);

        player2NameText = new JTextField(30);
        player2NameText.setMaximumSize(player2NameText.getPreferredSize());
        player2NameText.setAlignmentX(Component.CENTER_ALIGNMENT);
        gamePanel.add(player2NameText);

        goButton = new JButton("GO");
        goButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        gamePanel.add(goButton);

        gameModeLabel = new JLabel();
        gameModeLabel.setVisible(false);
        gamePanel.add(gameModeLabel);

        correctAnswerButton = new JButton("Correct Answer");
        correctAnswerButton.setVisible(false);
        gamePanel.add(correctAnswerButton);

        betButton = new JButton("Bet");
        betButton.setVisible(false);
        gamePanel.add(betButton);

        stopTheClockButton = new JButton("Stop The Clock");
        stopTheClockButton.setVisible(false);
        gamePanel.add(stopTheClockButton);

        quickAnswerButton = new JButton("Quick Answer");
        quickAnswerButton.setVisible(false);
        gamePanel.add(quickAnswerButton);

        thermometerButton = new JButton("Thermometer");
        thermometerButton.setVisible(false);
        gamePanel.add(thermometerButton);

        ActionListener goListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startLabel.setVisible(false);
                player1NameLabel.setVisible(false);
                player2NameLabel.setVisible(false);
                player1NameText.setVisible(false);
                player2NameText.setVisible(false);
                goButton.setVisible(false);

                player1 = new Player(player1NameText.getText(), 0);
                player2 = new Player(player2NameText.getText(), 0);

                gameModeLabel.setText("Hi " + player1NameText.getText() + " & " + player2NameText.getText() + "! What do you want to play?");
                gameModeLabel.setVisible(true);
                gameModeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                correctAnswerButton.setVisible(true);
                correctAnswerButton.setAlignmentX(Component.CENTER_ALIGNMENT);

                betButton.setVisible(true);
                betButton.setAlignmentX(Component.CENTER_ALIGNMENT);

                stopTheClockButton.setVisible(true);
                stopTheClockButton.setAlignmentX(Component.CENTER_ALIGNMENT);

                quickAnswerButton.setVisible(true);
                quickAnswerButton.setAlignmentX(Component.CENTER_ALIGNMENT);

                thermometerButton.setVisible(true);
                thermometerButton.setAlignmentX(Component.CENTER_ALIGNMENT);

                correctAnswerButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        correctAnswer();
                    }
                });

                betButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        bet();
                    }
                });

                stopTheClockButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        stopTheClock();
                    }
                });

                quickAnswerButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        quickAnswer();
                    }
                });

                thermometerButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        thermometer();
                    }
                });
            }
        };
        goButton.addActionListener(goListener);

        player1InstructionsLabel = new JLabel("Player 1 plays with: 1, 2, 3, 4");
        player1InstructionsLabel.setVisible(false);
        gamePanel.add(player1InstructionsLabel);

        player2InstructionsLabel = new JLabel("Player 2 plays with: q, w, e, r");
        player2InstructionsLabel.setVisible(false);
        gamePanel.add(player2InstructionsLabel);

        roundLabel = new JLabel();
        roundLabel.setVisible(false);
        gamePanel.add(roundLabel);

        categoryLabel = new JLabel();
        categoryLabel.setVisible(false);
        gamePanel.add(categoryLabel);

        imageLabel = new JLabel();
        imageLabel.setVisible(false);
        gamePanel.add(imageLabel);

        questionLabel = new JLabel();
        questionLabel.setVisible(false);
        gamePanel.add(questionLabel);

        player1Label = new JLabel("Player 1");
        player1Label.setVisible(false);
        gamePanel.add(player1Label);

        ans11 = new JRadioButton();
        ans11.setVisible(false);
        gamePanel.add(ans11);

        ans12 = new JRadioButton();
        ans12.setVisible(false);
        gamePanel.add(ans12);

        ans13 = new JRadioButton();
        ans13.setVisible(false);
        gamePanel.add(ans13);

        ans14 = new JRadioButton();
        ans14.setVisible(false);
        gamePanel.add(ans14);

        player2Label = new JLabel("Player 2");
        player2Label.setVisible(false);
        gamePanel.add(player2Label);

        ans21 = new JRadioButton();
        ans21.setVisible(false);
        gamePanel.add(ans21);

        ans22 = new JRadioButton();
        ans22.setVisible(false);
        gamePanel.add(ans22);

        ans23 = new JRadioButton();
        ans23.setVisible(false);
        gamePanel.add(ans23);

        ans24 = new JRadioButton();
        ans24.setVisible(false);
        gamePanel.add(ans24);

        nextQuestionButton = new JButton("NEXT");
        nextQuestionButton.setVisible(false);
        gamePanel.add(nextQuestionButton);

        player1ResultLabel = new JLabel();
        player1ResultLabel.setVisible(false);
        gamePanel.add(player1ResultLabel);

        player2ResultLabel = new JLabel();
        player2ResultLabel.setVisible(false);
        gamePanel.add(player2ResultLabel);

        player1AnswersGroup = new ButtonGroup();
        player2AnswersGroup = new ButtonGroup();

        this.categories = categories;
        this.answeredQuestions = 0;
        this.round = 0;
        this.player1Answered = false;
        this.player2Answered = false;
        this.player1CorrectAnswers = 0;
        this.player2CorrectAnswers = 0;
    }

    public void start() {
        gameFrame.setVisible(true);
    }

    private void initGameComponents() {
        gameModeLabel.setVisible(false);
        correctAnswerButton.setVisible(false);
        betButton.setVisible(false);
        stopTheClockButton.setVisible(false);
        quickAnswerButton.setVisible(false);
        thermometerButton.setVisible(false);
        player1InstructionsLabel.setVisible(true);
        player2InstructionsLabel.setVisible(true);
        roundLabel.setVisible(true);
        categoryLabel.setVisible(true);
        questionLabel.setVisible(true);
        player1Label.setVisible(true);
        player2Label.setVisible(true);
        player1ResultLabel.setVisible(true);
        player2ResultLabel.setVisible(true);
        ans11.setVisible(true);
        ans12.setVisible(true);
        ans13.setVisible(true);
        ans14.setVisible(true);
        player1AnswersGroup.add(ans11);
        player1AnswersGroup.add(ans12);
        player1AnswersGroup.add(ans13);
        player1AnswersGroup.add(ans14);
        ans21.setVisible(true);
        ans22.setVisible(true);
        ans23.setVisible(true);
        ans24.setVisible(true);
        player2AnswersGroup.add(ans21);
        player2AnswersGroup.add(ans22);
        player2AnswersGroup.add(ans23);
        player2AnswersGroup.add(ans24);
        nextQuestionButton.setVisible(true);
    }

    private void setQuestion() {
        player1AnswersGroup.clearSelection();
        player2AnswersGroup.clearSelection();
        nextQuestionButton.setEnabled(false);
        if (answeredQuestions%3 == 0) {
            round++;
        }
        if (round <= 4) {
            roundLabel.setText("Round " + (round));
            Random rand = new Random();
            Category category = categories.get(rand.nextInt(6));
            category.setIndex();
            question = category.getQuestion();
            categoryLabel.setText(category.getCategoryName());
            questionLabel.setText(question.getTitle());
            if (question.getHasImage()) {
                imageLabel.setVisible(true);
                imageLabel.setIcon(new ImageIcon(question.getImageFilename()));
            }

            setAnswers();
        } else {
            finishGame();
        }
    }

    private void setAnswers() {
        ArrayList<Answer> answers = question.getAnswers();
        ans11.setText(answers.get(0).getAns());
        ans21.setText(answers.get(0).getAns());
        ans12.setText(answers.get(1).getAns());
        ans22.setText(answers.get(1).getAns());
        ans13.setText(answers.get(2).getAns());
        ans23.setText(answers.get(2).getAns());
        ans14.setText(answers.get(3).getAns());
        ans24.setText(answers.get(3).getAns());
    }

    private void finishGame() {
        ResultsFile resultsFile = new ResultsFile("results.txt");
        if (player1.getScore() > player2.getScore()) {
            resultsFile.setPlayerResults(player1, 2);
            resultsFile.setPlayerResults(player2, 1);
            JOptionPane.showMessageDialog(gameFrame, "The game is over.\n" + player1.getName() + "' s score is: " + player1.getScore() + " points!\n" + player2.getName() + "' s score is: " + player2.getScore() + " points!" + "\nWINNER: " + player1.getName() + "!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        }
        else if (player1.getScore() < player2.getScore()) {
            resultsFile.setPlayerResults(player2, 2);
            resultsFile.setPlayerResults(player1, 1);
            JOptionPane.showMessageDialog(gameFrame, "The game is over.\n" + player1.getName() + "' s score is: " + player1.getScore() + " points!\n" + player2.getName() + "' s score is: " + player2.getScore() + " points!" + "\nWINNER: " + player2.getName() + "!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(gameFrame, "The game is over.\n" + player1.getName() + "' s score is: " + player1.getScore() + " points!\n" + player2.getName() + "' s score is: " + player2.getScore() + " points!" + "\nDRAW", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        }
        JOptionPane.showMessageDialog(gameFrame, player1.getName() + "\nHigh Score: " + player1.getHighScore() + " points.\nWins: " + player1.getWins() + "\n" + player2.getName() + "\nHigh Score: " + player2.getHighScore() + " points.\nWins: " + player2.getWins(), "Player Stats", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    private void setUpListeners() {
        gameFrame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                switch (e.getKeyChar()) {
                    case '1':
                        ans11.setSelected(true);
                        player1Answered = true;
                        if (player2Answered) {
                            nextQuestionButton.setEnabled(true);
                        }
                        break;
                    case '2':
                        ans12.setSelected(true);
                        player1Answered = true;
                        if (player2Answered) {
                            nextQuestionButton.setEnabled(true);
                        }
                        break;
                    case '3':
                        ans13.setSelected(true);
                        player1Answered = true;
                        if (player2Answered) {
                            nextQuestionButton.setEnabled(true);
                        }
                        break;
                    case '4':
                        ans14.setSelected(true);
                        player1Answered = true;
                        if (player2Answered) {
                            nextQuestionButton.setEnabled(true);
                        }
                        break;
                    case 'q':
                        ans21.setSelected(true);
                        player2Answered = true;
                        if (player1Answered) {
                            nextQuestionButton.setEnabled(true);
                        }
                        break;
                    case 'w':
                        ans22.setSelected(true);
                        player2Answered = true;
                        if (player1Answered) {
                            nextQuestionButton.setEnabled(true);
                        }
                        break;
                    case 'e':
                        ans23.setSelected(true);
                        player2Answered = true;
                        if (player1Answered) {
                            nextQuestionButton.setEnabled(true);
                        }
                        break;
                    case 'r':
                        ans24.setSelected(true);
                        player2Answered = true;
                        if (player1Answered) {
                            nextQuestionButton.setEnabled(true);
                        }
                        break;
                }
            }
        });
    }

    private void correctAnswer() {
        initGameComponents();

        nextQuestionCorrectAnswer();
        setUpListenersCorrectAnswer();
    }

    private void nextQuestionCorrectAnswer() {
        setQuestion();
    }

    private void setUpListenersCorrectAnswer() {
        nextQuestionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answeredQuestions++;
                player1Answered = false;
                player2Answered = false;
                int player1SelectedAnswer = 0;
                int player2SelectedAnswer = 0;
                if (ans11.isSelected()) {
                    player1SelectedAnswer = 1;
                } else if (ans12.isSelected()) {
                    player1SelectedAnswer = 2;
                } else if (ans13.isSelected()) {
                    player1SelectedAnswer = 3;
                } else if (ans14.isSelected()) {
                    player1SelectedAnswer = 4;
                }
                if (ans21.isSelected()) {
                    player2SelectedAnswer = 1;
                } else if (ans22.isSelected()) {
                    player2SelectedAnswer = 2;
                } else if (ans23.isSelected()) {
                    player2SelectedAnswer = 3;
                } else if (ans24.isSelected()) {
                    player2SelectedAnswer = 4;
                }
                player1ResultLabel.setText(player1.getName() + " " + player1.correctAnswer(question.checkQuestion(player1SelectedAnswer), question.getRightAns()) + " points!");
                player2ResultLabel.setText(player2.getName() + " " + player2.correctAnswer(question.checkQuestion(player2SelectedAnswer), question.getRightAns()) + " points!");
                imageLabel.setVisible(false);
                nextQuestionCorrectAnswer();
            }
        });

        setUpListeners();
    }

    private void bet() {
        initGameComponents();

        nextQuestionBet();
        setUpListenersBet();
    }

    private void nextQuestionBet() {
        player1AnswersGroup.clearSelection();
        player2AnswersGroup.clearSelection();
        nextQuestionButton.setEnabled(false);
        if (answeredQuestions%3 == 0) {
            round++;
        }
        if (round <= 4) {
            roundLabel.setText("Round " + (round));
            Random rand = new Random();
            Category category = categories.get(rand.nextInt(6));
            category.setIndex();
            question = category.getQuestion();

            Integer[] bets = {250,500,750,1000};
            int player1Bet = (Integer) JOptionPane.showInputDialog(gamePanel,"Category: " + category.getCategoryName(),"Player 1 Bet",JOptionPane.PLAIN_MESSAGE,null,bets,bets[0]);
            player1.placeBet(player1Bet);
            int player2Bet = (Integer) JOptionPane.showInputDialog(gamePanel,"Category: " + category.getCategoryName(),"Player 2 Bet",JOptionPane.PLAIN_MESSAGE,null,bets,bets[0]);
            player2.placeBet(player2Bet);

            categoryLabel.setText(category.getCategoryName());
            questionLabel.setText(question.getTitle());
            if (question.getHasImage()) {
                imageLabel.setVisible(true);
                imageLabel.setIcon(new ImageIcon(question.getImageFilename()));
            }

            setAnswers();
        } else {
            finishGame();
        }
    }

    private void setUpListenersBet() {
        nextQuestionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answeredQuestions++;
                player1Answered = false;
                player2Answered = false;
                int player1SelectedAnswer = 0;
                int player2SelectedAnswer = 0;
                if (ans11.isSelected()) {
                    player1SelectedAnswer = 1;
                } else if (ans12.isSelected()) {
                    player1SelectedAnswer = 2;
                } else if (ans13.isSelected()) {
                    player1SelectedAnswer = 3;
                } else if (ans14.isSelected()) {
                    player1SelectedAnswer = 4;
                }
                if (ans21.isSelected()) {
                    player2SelectedAnswer = 1;
                } else if (ans22.isSelected()) {
                    player2SelectedAnswer = 2;
                } else if (ans23.isSelected()) {
                    player2SelectedAnswer = 3;
                } else if (ans24.isSelected()) {
                    player2SelectedAnswer = 4;
                }
                player1ResultLabel.setText(player1.getName() + " " + player1.Bet(question.checkQuestion(player1SelectedAnswer), question.getRightAns()) + " points!");
                player2ResultLabel.setText(player2.getName() + " " + player2.Bet(question.checkQuestion(player2SelectedAnswer), question.getRightAns()) + " points!");
                imageLabel.setVisible(false);
                nextQuestionBet();
            }
        });

        setUpListeners();
    }

    private void stopTheClock() {
        initGameComponents();

        nextQuestionStopTheClock();
        setUpListenersStopTheClock();
    }

    private void nextQuestionStopTheClock() {
        setQuestion();
        start = Instant.now();
    }

    private void setUpListenersStopTheClock() {
        nextQuestionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answeredQuestions++;
                player1Answered = false;
                player2Answered = false;
                int player1SelectedAnswer = 0;
                int player2SelectedAnswer = 0;
                if (ans11.isSelected()) {
                    player1SelectedAnswer = 1;
                } else if (ans12.isSelected()) {
                    player1SelectedAnswer = 2;
                } else if (ans13.isSelected()) {
                    player1SelectedAnswer = 3;
                } else if (ans14.isSelected()) {
                    player1SelectedAnswer = 4;
                }
                if (ans21.isSelected()) {
                    player2SelectedAnswer = 1;
                } else if (ans22.isSelected()) {
                    player2SelectedAnswer = 2;
                } else if (ans23.isSelected()) {
                    player2SelectedAnswer = 3;
                } else if (ans24.isSelected()) {
                    player2SelectedAnswer = 4;
                }
                if (Duration.between(start, player1Finish).toMillis() > 5000) {
                    player1ResultLabel.setText("To late! Your score so far is: " + player1.getScore());
                }
                else {
                    player1ResultLabel.setText(player1.stopTheClock(question.checkQuestion(player1SelectedAnswer), 5000 - Duration.between(start, player1Finish).toMillis(), question.getRightAns()));
                }
                if (Duration.between(start, player2Finish).toMillis() > 5000) {
                    player2ResultLabel.setText("To late! Your score so far is: " + player2.getScore());
                }
                else {
                    player2ResultLabel.setText(player2.stopTheClock(question.checkQuestion(player2SelectedAnswer), 5000 - Duration.between(start, player2Finish).toMillis(), question.getRightAns()));
                }
                imageLabel.setVisible(false);
                nextQuestionStopTheClock();
            }
        });

        gameFrame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                switch (e.getKeyChar()) {
                    case '1':
                        ans11.setSelected(true);
                        player1Finish = Instant.now();
                        player1Answered = true;
                        if (player2Answered) {
                            nextQuestionButton.setEnabled(true);
                        }
                        break;
                    case '2':
                        ans12.setSelected(true);
                        player1Finish = Instant.now();
                        player1Answered = true;
                        if (player2Answered) {
                            nextQuestionButton.setEnabled(true);
                        }
                        break;
                    case '3':
                        ans13.setSelected(true);
                        player1Finish = Instant.now();
                        player1Answered = true;
                        if (player2Answered) {
                            nextQuestionButton.setEnabled(true);
                        }
                        break;
                    case '4':
                        ans14.setSelected(true);
                        player1Finish = Instant.now();
                        player1Answered = true;
                        if (player2Answered) {
                            nextQuestionButton.setEnabled(true);
                        }
                        break;
                    case 'q':
                        ans21.setSelected(true);
                        player2Finish = Instant.now();
                        player2Answered = true;
                        if (player1Answered) {
                            nextQuestionButton.setEnabled(true);
                        }
                        break;
                    case 'w':
                        ans22.setSelected(true);
                        player2Finish = Instant.now();
                        player2Answered = true;
                        if (player1Answered) {
                            nextQuestionButton.setEnabled(true);

                        }
                        break;
                    case 'e':
                        ans23.setSelected(true);
                        player2Finish = Instant.now();
                        player2Answered = true;
                        if (player1Answered) {
                            nextQuestionButton.setEnabled(true);
                        }
                        break;
                    case 'r':
                        ans24.setSelected(true);
                        player2Finish = Instant.now();
                        player2Answered = true;
                        if (player1Answered) {
                            nextQuestionButton.setEnabled(true);
                        }
                        break;
                }
            }
        });
    }

    private void quickAnswer() {
        initGameComponents();

        nextQuestionQuickAnswer();
        setUpListenersQuickAnswer();
    }

    private void nextQuestionQuickAnswer() {
        nextQuestionButton.setVisible(false);
        setQuestion();
    }

    private void setUpListenersQuickAnswer() {
        gameFrame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                switch (e.getKeyChar()) {
                    case '1':
                        ans11.setSelected(true);
                        player1Answered = true;
                        if (question.checkQuestion(1)) {
                            if (player2Answered) {
                                player1ResultLabel.setText(player1.getName() + " " + player1.quickAnswer(question.checkQuestion(1), 1000, question.getRightAns()) + " points!");
                                player2ResultLabel.setText(player2.getName() + " " + player2.quickAnswer(false, 500, question.getRightAns()) + " points!");
                            }
                            else {
                                player1ResultLabel.setText(player1.getName() + " " + player1.quickAnswer(question.checkQuestion(1), 1000, question.getRightAns()) + " points!");
                                player2ResultLabel.setText(player2.getName() + " " + player2.quickAnswer(false, 0, question.getRightAns()) + " points!");
                            }
                            answeredQuestions++;
                            player1Answered = false;
                            player2Answered = false;
                            imageLabel.setVisible(false);
                            nextQuestionQuickAnswer();
                        }
                        break;
                    case '2':
                        ans12.setSelected(true);
                        player1Answered = true;
                        if (question.checkQuestion(2)) {
                            if (player2Answered) {
                                player1ResultLabel.setText(player1.getName() + " " + player1.quickAnswer(question.checkQuestion(2), 1000, question.getRightAns()) + " points!");
                                player2ResultLabel.setText(player2.getName() + " " + player2.quickAnswer(false, 500, question.getRightAns()) + " points!");
                            }
                            else {
                                player1ResultLabel.setText(player1.getName() + " " + player1.quickAnswer(question.checkQuestion(2), 1000, question.getRightAns()) + " points!");
                                player2ResultLabel.setText(player2.getName() + " " + player2.quickAnswer(false, 0, question.getRightAns()) + " points!");
                            }
                            answeredQuestions++;
                            player1Answered = false;
                            player2Answered = false;
                            imageLabel.setVisible(false);
                            nextQuestionQuickAnswer();
                        }
                        break;
                    case '3':
                        ans13.setSelected(true);
                        player1Answered = true;
                        if (question.checkQuestion(3)) {
                            if (player2Answered) {
                                player1ResultLabel.setText(player1.getName() + " " + player1.quickAnswer(question.checkQuestion(3), 1000, question.getRightAns()) + " points!");
                                player2ResultLabel.setText(player2.getName() + " " + player2.quickAnswer(false, 500, question.getRightAns()) + " points!");
                            }
                            else {
                                player1ResultLabel.setText(player1.getName() + " " + player1.quickAnswer(question.checkQuestion(3), 1000, question.getRightAns()) + " points!");
                                player2ResultLabel.setText(player2.getName() + " " + player2.quickAnswer(false, 0, question.getRightAns()) + " points!");
                            }
                            answeredQuestions++;
                            player1Answered = false;
                            player2Answered = false;
                            imageLabel.setVisible(false);
                            nextQuestionQuickAnswer();
                        }
                        break;
                    case '4':
                        ans14.setSelected(true);
                        player1Answered = true;
                        if (question.checkQuestion(4)) {
                            if (player2Answered) {
                                player1ResultLabel.setText(player1.getName() + " " + player1.quickAnswer(question.checkQuestion(4), 1000, question.getRightAns()) + " points!");
                                player2ResultLabel.setText(player2.getName() + " " + player2.quickAnswer(false, 500, question.getRightAns()) + " points!");
                            }
                            else {
                                player1ResultLabel.setText(player1.getName() + " " + player1.quickAnswer(question.checkQuestion(4), 1000, question.getRightAns()) + " points!");
                                player2ResultLabel.setText(player2.getName() + " " + player2.quickAnswer(false, 0, question.getRightAns()) + " points!");
                            }
                            answeredQuestions++;
                            player1Answered = false;
                            player2Answered = false;
                            imageLabel.setVisible(false);
                            nextQuestionQuickAnswer();
                        }
                        break;
                    case 'q':
                        ans21.setSelected(true);
                        player2Answered = true;
                        if (question.checkQuestion(1)) {
                            if (player1Answered) {
                                player1ResultLabel.setText(player1.getName() + " " + player1.quickAnswer(false, 500, question.getRightAns()) + " points!");
                                player2ResultLabel.setText(player2.getName() + " " + player2.quickAnswer(question.checkQuestion(1), 1000, question.getRightAns()) + " points!");
                            }
                            else {
                                player1ResultLabel.setText(player1.getName() + " " + player1.quickAnswer(false, 0, question.getRightAns()) + " points!");
                                player2ResultLabel.setText(player2.getName() + " " + player2.quickAnswer(question.checkQuestion(1), 1000, question.getRightAns()) + " points!");
                            }
                            answeredQuestions++;
                            player1Answered = false;
                            player2Answered = false;
                            imageLabel.setVisible(false);
                            nextQuestionQuickAnswer();
                        }
                        break;
                    case 'w':
                        ans22.setSelected(true);
                        player2Answered = true;
                        if (question.checkQuestion(2)) {
                            if (player1Answered) {
                                player1ResultLabel.setText(player1.getName() + " " + player1.quickAnswer(false, 500, question.getRightAns()) + " points!");
                                player2ResultLabel.setText(player2.getName() + " " + player2.quickAnswer(question.checkQuestion(2), 1000, question.getRightAns()) + " points!");
                            }
                            else {
                                player1ResultLabel.setText(player1.getName() + " " + player1.quickAnswer(false, 0, question.getRightAns()) + " points!");
                                player2ResultLabel.setText(player2.getName() + " " + player2.quickAnswer(question.checkQuestion(2), 1000, question.getRightAns()) + " points!");
                            }
                            answeredQuestions++;
                            player1Answered = false;
                            player2Answered = false;
                            imageLabel.setVisible(false);
                            nextQuestionQuickAnswer();
                        }
                        break;
                    case 'e':
                        ans23.setSelected(true);
                        player2Answered = true;
                        if (question.checkQuestion(3)) {
                            if (player1Answered) {
                                player1ResultLabel.setText(player1.getName() + " " + player1.quickAnswer(false, 500, question.getRightAns()) + " points!");
                                player2ResultLabel.setText(player2.getName() + " " + player2.quickAnswer(question.checkQuestion(3), 1000, question.getRightAns()) + " points!");
                            }
                            else {
                                player1ResultLabel.setText(player1.getName() + " " + player1.quickAnswer(false, 0, question.getRightAns()) + " points!");
                                player2ResultLabel.setText(player2.getName() + " " + player2.quickAnswer(question.checkQuestion(3), 1000, question.getRightAns()) + " points!");
                            }
                            answeredQuestions++;
                            player1Answered = false;
                            player2Answered = false;
                            imageLabel.setVisible(false);
                            nextQuestionQuickAnswer();
                        }
                        break;
                    case 'r':
                        ans24.setSelected(true);
                        player2Answered = true;
                        if (question.checkQuestion(4)) {
                            if (player1Answered) {
                                player1ResultLabel.setText(player1.getName() + " " + player1.quickAnswer(false, 500, question.getRightAns()) + " points!");
                                player2ResultLabel.setText(player2.getName() + " " + player2.quickAnswer(question.checkQuestion(4), 1000, question.getRightAns()) + " points!");
                            }
                            else {
                                player1ResultLabel.setText(player1.getName() + " " + player1.quickAnswer(false, 0, question.getRightAns()) + " points!");
                                player2ResultLabel.setText(player2.getName() + " " + player2.quickAnswer(question.checkQuestion(4), 1000, question.getRightAns()) + " points!");
                            }
                            answeredQuestions++;
                            player1Answered = false;
                            player2Answered = false;
                            imageLabel.setVisible(false);
                            nextQuestionQuickAnswer();
                        }
                        break;
                }
            }
        });
    }

    private void thermometer() {
        initGameComponents();

        nextQuestionThermometer();
        setUpListenersThermometer();
    }

    private void nextQuestionThermometer() {
        setQuestion();
    }

    private void setUpListenersThermometer() {
        nextQuestionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answeredQuestions++;
                player1Answered = false;
                player2Answered = false;
                int player1SelectedAnswer = 0;
                int player2SelectedAnswer = 0;
                if (ans11.isSelected()) {
                    player1SelectedAnswer = 1;
                } else if (ans12.isSelected()) {
                    player1SelectedAnswer = 2;
                } else if (ans13.isSelected()) {
                    player1SelectedAnswer = 3;
                } else if (ans14.isSelected()) {
                    player1SelectedAnswer = 4;
                }
                if (ans21.isSelected()) {
                    player2SelectedAnswer = 1;
                } else if (ans22.isSelected()) {
                    player2SelectedAnswer = 2;
                } else if (ans23.isSelected()) {
                    player2SelectedAnswer = 3;
                } else if (ans24.isSelected()) {
                    player2SelectedAnswer = 4;
                }
                if (question.checkQuestion(player1SelectedAnswer)) {
                    player1CorrectAnswers += 1;
                }
                if (question.checkQuestion(player2SelectedAnswer)) {
                    player2CorrectAnswers += 1;
                }
                player1ResultLabel.setText(player1.getName() + " " + player1.thermometer(question.checkQuestion(player1SelectedAnswer), player1CorrectAnswers, question.getRightAns()) + " points!");
                player2ResultLabel.setText(player2.getName() + " " + player2.thermometer(question.checkQuestion(player2SelectedAnswer), player2CorrectAnswers, question.getRightAns()) + " points!");
                imageLabel.setVisible(false);
                nextQuestionThermometer();
            }
        });

        setUpListeners();
    }
}
