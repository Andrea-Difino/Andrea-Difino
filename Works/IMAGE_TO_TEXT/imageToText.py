from distutils.log import error
from encodings import utf_8
from importlib.resources import open_binary
import pytesseract as tess
tess.pytesseract.tesseract_cmd = r'C:\Users\ANDREA\OneDrive\Desktop\Tesseract-OCR\tesseract.exe' #folder directory to change
from PIL import Image

file_name = input('Nome del File(con anche estensione):')
img = Image.open(file_name)
text = tess.image_to_string(img)

#open text file
text_file = open('result-text.txt', 'wb')
#write string to file
text_file.write(text.encode('utf8'))
#close file
text_file.close()
#open the file to read
text_file = open('result-text.txt', 'rb')

print(text_file.read().decode('utf8'))
