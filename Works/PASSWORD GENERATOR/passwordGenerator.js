const chars = "abcdefghijklmnopqrstuv[]\//|-_.!£$%&ABCDEFGHIJKLMNOPQRSTUVWXYZ";

var passwordLength = 18;
var password = "";

for (let i = 0; i < passwordLength; i++) {
   var randomNumber = Math.floor(Math.random() * chars.length);
   password += chars.substring(randomNumber,randomNumber+1)
};

console.log(password);
