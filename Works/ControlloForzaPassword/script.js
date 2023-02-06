const _v = {
    hasError : false,
    isValidPassword : false,
    emailPattern : /^[^\s@]+@[^\s@]+\.[^\s@]+$/
}

function formValidation(form, notifica) {
    _v.form = document.querySelector(`${form}`);
    _v.notificationItem = document.querySelector(`${notifica}`);
    _v.passwordStrength = document.querySelectorAll('#password > span');
    _v.formItems = Array.from(_v.form.elements);
    checkPasswordStrength();
    submitForm();
}

function submitForm(){
    _v.form.addEventListener('submit', (e)=>{
        e.stopPropagation();
        e.preventDefault();
        checkValidation();
    }, true)
}

function checkValidation() {
    try {
        //controllo campi obbligatori
        requiredFields();
        //controllo email
        isValidEmail();
        //controllo coincidenza password e conferma password
        checkPassword();

        //controlli superati
        _v.notificationItem.className = 'notification-success';
        _v.notificationItem.textContent = 'La registrazione è avvenuta correttamente.';
        resetForm();
    } catch(e) {
        _v.notificationItem.className = 'notification-error';
        _v.notificationItem.textContent = e.message;
    }
}

function requiredFields(){
    let error;
    _v.hasError = false;
    _v.formItems.forEach(item => {
        error = false;
        if(item.type !== 'checkbox' && item.required && item.value === ''){
            error = true;
        }
        if(item.type === 'checkbox' && item.required && !item.checked){
            error = true;
        }
        if(error && item.type !== "submit") {
            _v.hasError = true;
            item.classList.add('error');
        }
    });
    if(_v.hasError){
        throw new Error('Compilare i campi obbligatori');
    }
}

function isValidEmail(){
    if(!_v.emailPattern.test(_v.form.email.value)){
        throw new Error('Email indicata non valida');
    }
}

function checkPasswordStrength(){
    _v.form.password.addEventListener('keyup', (e) =>
    {
        const isValid = {
            isLow : false,
            isHigh : false
        },
        pwd = e.target.value;
        resetStrength();
        if(pwd.length >= 8) {
            _v.passwordStrength[0].classList.add('active')
            if(regexCount(/[&%$£!#@]/g, pwd) === 1){
                _v.passwordStrength[1].classList.add('active')
            }
            isValid.isLow = true;
        }
        if(pwd.length >= 10 && regexCount(/[&?!%]/g, pwd) > 1 && regexCount(/[A-Z]/g, pwd) > 0){
            _v.passwordStrength.forEach((item) => {
                item.classList.add('active');
            });
            isValid.isHigh = true;
        }
        _v.isValidPassword = (isValid.isLow || isValid.isHigh) ? true : false;
    })
}

function resetStrength() {
    _v.passwordStrength.forEach((item) => {
        item.classList.remove('active');
    });
}

function regexCount(pattern, stringa) {
    return (stringa.match(pattern) || []).length;
}

function checkPassword(){
    const pwd = _v.form.password.value,
            re_pwd = _v.form.re_password.value;

    if(!_v.isValidPassword){
        throw new Error('Password non valida');
    }
    if (pwd !== re_pwd) {
        throw new Error('Le password non coincidono');
    }
}

function resetForm() {    
    resetStrength();
    _v.form.reset();
    _v.formItems.forEach(item => {
        item.classList.remove("error");
    });   
}
export default formValidation;

