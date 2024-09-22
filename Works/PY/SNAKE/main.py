from tkinter import *

def startGame():
    button.pack_forget()  


window = Tk()
window.geometry("600x600")
window.title("SNAKE")
icon = PhotoImage(file="snakeIcon.png")
window.iconphoto(True,icon)
window.config(background="white")

button = Button(window,
                text="START THE GAME",
                command=startGame,
                justify="center",
                font=("Comic Sans",25),
                foreground="green",
                background="white",
                activeforeground="darkgreen")

rows, cols = 10 , 10
tile_size = 50

button.place(x=250,y=250)
chessboard = Canvas(window, width=cols * tile_size, height=rows * tile_size)
chessboard.place(x=0, y=0)

# Colors for the chessboard squares
color1 = "#a1cf49"  # Light square
color2 = "#83a93b"  # Dark square

# Draw the chessboard
for row in range(rows):
    for col in range(cols):
        # Alternate the color between light and dark
        color = color1 if (row + col) % 2 == 0 else color2
        # Draw a rectangle (chessboard tile)
        chessboard.create_rectangle(col * tile_size, row * tile_size, 
                                    (col + 1) * tile_size, (row + 1) * tile_size, 
                                    fill=color, outline="")


    

window.mainloop() #place winwod on computer screen
