# Author: SlavicDonut

def display_board():
    print("   1   2   3\n"
          "A  {} | {} | {}\n"
          "B  {} | {} | {}\n"
          "C  {} | {} | {}".format(board["A"][0], board["A"][1], board["A"][2],
                                   board["B"][0], board["B"][1], board["B"][2],
                                   board["C"][0], board["C"][1], board["C"][2], ))


def is_valid(line, column):
    try:
        if line in board.keys():
            if 0 < int(column) <= 3:
                if board[line][int(column) - 1] == "-":
                    return True
                else:
                    return False
    except:
        return False


def tick(o_x, line, column):
    board[line][int(column) - 1] = o_x


def victory_check(o_x):
    if board["A"][0] == board["A"][1] == board["A"][2] == o_x:
        return True
    elif board["B"][0] == board["B"][1] == board["B"][2] == o_x:
        return True
    elif board["C"][0] == board["C"][1] == board["C"][2] == o_x:
        return True
    elif board["A"][0] == board["B"][0] == board["C"][0] == o_x:
        return True
    elif board["A"][1] == board["B"][1] == board["C"][1] == o_x:
        return True
    elif board["A"][2] == board["B"][2] == board["C"][2] == o_x:
        return True
    elif board["A"][0] == board["B"][1] == board["C"][2] == o_x:
        return True
    elif board["C"][0] == board["B"][1] == board["A"][2] == o_x:
        return True
    else:
        return False


victory = False
board = {"A": ["-", "-", "-", ], "B": ["-", "-", "-", ], "C": ["-", "-", "-", ]}
column_values = [1, 2, 3]
player = "o"

display_board()
while not victory:
    position = input("now it's {} guy tour: ".format(player))
    if is_valid(position[0], position[1]):
        tick(player, position[0], position[1])
    else:
        print("you did it wrong")
        continue
    victory = victory_check(player)
    if victory:
        display_board()
        print("{} guy won!".format(player))
        break
    if player == "o":
        player = "x"
    else:
        player = "o"
    display_board()
