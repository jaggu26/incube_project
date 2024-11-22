function handleAreaOfInterestChange(selectedValue) {
	debugger

	const xhr = new XMLHttpRequest();
	xhr.open('POST', '/handleAreaOfInterestChange', true);
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xhr.onreadystatechange = function() {
		debugger
		if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
			const response = xhr.responseText;
			if (response === "User is Already Registered for this Course.") {
				alert(response);
				// Unselect the selected course option
				const areaOfInterestSelect = document.getElementById('areaOfInterest');
				areaOfInterestSelect.value = '';
			}
		}
	};
	xhr.send('areaOfInterest=' + encodeURIComponent(selectedValue));
}

window.onload = function() {
	getCourceDropDown();
}

function getCourceDropDown() {
	debugger
	const xhr = new XMLHttpRequest();
	xhr.open('POST', '/getCourceDropDown', true);
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

	xhr.onreadystatechange = function() {
		if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
			const response = xhr.responseText;
			const areaOfInterestSelect = document.getElementById('areaOfInterest');
			areaOfInterestSelect.innerHTML = response; // Set the innerHTML of the select element
		}
	};

	xhr.send();
}

const passwordInput = document.getElementById('password');
const togglePasswordIcon = document.getElementById('togglePassword');

togglePasswordIcon.addEventListener('click', function() {
	const type = passwordInput.getAttribute('type') === 'password' ? 'text' : 'password';
	passwordInput.setAttribute('type', type);
	this.classList.toggle('fa-eye-slash');
	this.classList.toggle('fa-eye');
});


function checkUsernameLength(input) {
	debugger
	if (input.value.length === 8) {
		var username = input.value;
		yourFunction(username);
	}
}

function yourFunction(inputUsername) {
	debugger;
	alert("hi.. it's 8 characters reached");

	const xhr = new XMLHttpRequest();
	xhr.open('POST', '/checkUserNameAlreadyExist', true);
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

	// Encode the inputUsername value as a URL parameter
	const params = 'inputUsername=' + encodeURIComponent(inputUsername);

	xhr.onreadystatechange = function() {
		if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
			//alert('Username is Already taken, Please Use another username..!');
			const response = xhr.responseText;
			alert(response);
		}
	};

	// Sending the inputUsername as part of the request payload
	xhr.send(params);
}


/* <-----REFERANCE JS FOR REQUEST----->
function getCourceDropDown() {
	debugger
	const xhr = new XMLHttpRequest();
	xhr.open('POST', '/getCourceDropDown', true);
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

	xhr.onreadystatechange = function() {
		if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
			const response = xhr.responseText;
			const areaOfInterestSelect = document.getElementById('areaOfInterest');
			areaOfInterestSelect.innerHTML = response; // Set the innerHTML of the select element
		}
	};

	xhr.send();
}*/
