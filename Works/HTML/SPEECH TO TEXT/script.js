const startButton = document.getElementById('startButton');
const stopButton = document.getElementById('stopButton');
const outputDiv = document.getElementById('output');
const deleteBtn = document.getElementById('delete');
const recognition = new (window.SpeechRecognition || window.webkitSpeechRecognition || window.mozSpeechRecognition || window.msSpeechRecognition)();

var userLang = navigator.language || navigator.userLanguage; 
recognition.lang = `${userLang}`;

let recognizing = false; // Flag to keep track if recognition should keep going

deleteF = () => {
    outputDiv.textContent = "";
}

recognition.onstart = () => {
    startButton.textContent = 'Listening...';
    recognizing = true; // Set recognizing to true when it starts
};

recognition.onresult = (event) => {
    let transcript = event.results[0][0].transcript;
    console.log(transcript);
    outputDiv.textContent += transcript + ". ";
};

recognition.onerror = (event) => {
    console.log(event);
};

recognition.onend = () => {
    if (recognizing) {
        recognition.start(); // Automatically restart recognition if still recognizing
    } else {
        startButton.textContent = 'Start Voice Input'; // Reset button text if recognition was stopped manually
    }
};

// Start recognition
startButton.addEventListener('click', () => {
    if (!recognizing) {
        recognizing = true;
        recognition.start();
    }
});

// Stop recognition
stopButton.addEventListener('click', () => {
    recognizing = false;
    recognition.stop(); // Manually stop the recognition
    startButton.textContent = 'Start Voice Input';
});

// Clear output
deleteBtn.addEventListener('click', () => {
    deleteF();
});