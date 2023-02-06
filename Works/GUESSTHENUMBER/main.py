import random

def guess(x):
    random_number = random.randint(1, x)
    guess = 0;
    while guess != random_number: #the condition is true until you find the right answer  
        guess = int(input(f"Try to guess the number between 1 and {x}:"))
        if guess < random_number:
            print("The number is too low")
        elif guess > random_number:
            print("The number is too High")
    
    print(f"Yesss, {guess} is the right answer")

guess(10) #max range of the number