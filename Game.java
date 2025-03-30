public class Game {
    private String[][] gameBoard = new String[10][10];
    private String[][] visBoard = new String[10][10];
    private int flagCount;

    public Game() {
        for(int var1 = 0; var1 < 10; ++var1) {
            for(int var2 = 0; var2 < 10; ++var2) {
                this.gameBoard[var1][var2] = "■";
                this.visBoard[var1][var2] = "■";
            }
        }

    }

    public String toString() {
        String var1 = "   0 1 2 3 4 5 6 7 8 9\n  ▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄\n";

        for(int var2 = 0; var2 < 10; ++var2) {
            var1 = var1 + var2 + " █";

            for(int var3 = 0; var3 < 10; ++var3) {
                var1 = var1 + this.visBoard[var3][var2];
                if (var3 < 9) {
                    var1 = var1 + " ";
                }
            }

            var1 = var1 + "█\n";
        }
        var1 +=  "  ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀";
        return var1;
    }

    public void addMines(int var1, int var2) {
        int var3 = 0;

        while(true) {
            int var4;
            int var5;
            do {
                do {
                    if (var3 >= 18) {
                        for(var4 = 0; var4 < this.gameBoard.length; ++var4) {
                            for(var5 = 0; var5 < this.gameBoard[0].length; ++var5) {
                                if (!this.gameBoard[var4][var5].equals("M")) {
                                    this.gameBoard[var4][var5] = this.numMines(var4, var5).toString();
                                }
                            }
                        }

                        return;
                    }

                    var4 = (int)(Math.random() * 10.0 - 0.5);
                    var5 = (int)(Math.random() * 10.0 - 0.5);
                } while(this.gameBoard[var4][var5] != "■");
            } while(var4 >= var1 - 1 && var4 <= var1 + 1 && var5 >= var2 - 1 && var5 <= var2 + 1);

            this.gameBoard[var4][var5] = "M";
            ++var3;
            ++this.flagCount;
        }
    }

    public int getFlagCount() {
        return this.flagCount;
    }

    public boolean digSpot(int var1, int var2) {
        if (this.gameBoard[var1][var2].equals("M") && !this.visBoard[var1][var2].equals("⚐")) {
            return true;
        } else if (this.visBoard[var1][var2].equals("⚐")) {
            return false;
        } else {
            Integer var3 = Integer.parseInt(this.gameBoard[var1][var2]);
            if (var3 != 0) {
                this.visBoard[var1][var2] = var3.toString();
            } else {
                this.visBoard[var1][var2] = " ";
            }

            if (!this.gameBoard[var1][var2].equals("M") && var3 == 0) {
                for(int var4 = -1; var4 < 2; ++var4) {
                    for(int var5 = -1; var5 < 2; ++var5) {
                        if (var1 + var4 < 10 && var1 + var4 > -1 && var2 + var5 < 10 && var2 + var5 > -1 && this.visBoard[var1 + var4][var2 + var5].equals("■")) {
                            if ((var4 != -1 || var5 != -1) && (var4 != 1 || var5 != 1) && (var4 != 1 || var5 != -1) && (var4 != -1 || var2 != 1)) {
                                this.digSpot(var1 + var4, var2 + var5);
                            } else if (Integer.parseInt(this.gameBoard[var1 + var4][var2 + var5]) != 0) {
                                this.digSpot(var1 + var4, var2 + var5);
                            }
                        }
                    }
                }
            }

            return false;
        }
    }

    public Integer numMines(int var1, int var2) {
        int var3 = 0;

        for(int var4 = -1; var4 < 2; ++var4) {
            for(int var5 = -1; var5 < 2; ++var5) {
                if (var1 + var4 <= 9 && var1 + var4 >= 0 && var2 + var5 <= 9 && var2 + var5 >= 0 && this.gameBoard[var1 + var4][var2 + var5].equals("M")) {
                    ++var3;
                }
            }
        }

        return var3;
    }

    public boolean checkWin() {
        int var1 = 0;

        for(int var2 = 0; var2 < 10; ++var2) {
            for(int var3 = 0; var3 < 10; ++var3) {
                if (this.visBoard[var3][var2].equals("■") || this.visBoard[var3][var2] == "⚐") {
                    ++var1;
                }
            }
        }

        if (var1 == 18) {
            return true;
        } else {
            return false;
        }
    }

    public void addFlag(int var1, int var2) {
        if (this.visBoard[var1][var2].equals("⚐")) {
            this.visBoard[var1][var2] = "■";
            ++this.flagCount;
        } else if (this.visBoard[var1][var2].equals("■") && this.flagCount > 0) {
            this.visBoard[var1][var2] = "⚐";
            --this.flagCount;
        }

    }
}