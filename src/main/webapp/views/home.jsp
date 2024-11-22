<!-- <!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .container {
            text-align: center;
            padding: 20px;
            background-color: #f2f2f2;
            border-radius: 5px;
        }

        .hidden {
            display: none;
        }
    </style>
</head>

<body>
    <div class="container">
        <div id="welcomeMessage">
            <h2>Welcome!</h2>
            <p>Register to get started.</p>
            <button id="registerBtn">Register</button>
        </div>
        <form action="/checkMobileNumExist" method="post">
            <div id="registrationForm" class="hidden">
                <h2>Enter Mobile Number</h2>
                <input type="text" id="mobileNumberInput" placeholder="Mobile Number" name="mobileNumber">
                <button id="proceedBtn">Proceed</button>
            </div>
        </form>
    </div>
    <script>
        const welcomeMessage = document.getElementById('welcomeMessage');
        const registrationForm = document.getElementById('registrationForm');
        const registerBtn = document.getElementById('registerBtn');

        registerBtn.addEventListener('click', () => {
            welcomeMessage.classList.add('hidden');
            registrationForm.classList.remove('hidden');
        });
    </script>
    
</body>

</html> -->

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Incube - Technical Training Registration</title>
    <style>
        /* styles.css */
        body {
            font-family: 'Poppins', sans-serif;
            background-color: #f8f8f8;
            color: #333;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .container {
            max-width: 331px;
            padding: 40px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        .logo-container {
            background-color: #4d90fe;
            padding: 20px;
            border-radius: 8px;
            margin-bottom: 20px;
        }

        .logo {
            max-width: 150px;
        }

        .hidden {
            display: none;
        }

        h1,
        h2 {
            margin-top: 0;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-input {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
        }

        .form-input:focus {
            outline: none;
            border-color: #4d90fe;
            box-shadow: 0 0 5px rgba(77, 144, 254, 0.5);
        }

        .btn {
            display: inline-block;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .btn-primary {
            background-color: #4d90fe;
            color: #fff;
        }

        .btn-primary:hover {
            background-color: #3276e2;
        }
    </style>
    <!-- <link rel="stylesheet" href="styles.css"> -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600;800&display=swap" rel="stylesheet">
</head>

<body>
    <div class="container">
        <div class="logo-container">
            <img src="https://www.myincube.com/assets/images/incube-logo.png" alt="Incube Logo" class="logo">
        </div>
        <div id="welcomeMessage">
            <h1>Welcome to Incube</h1>
            <p>Register to enroll in our cutting-edge technical training programs.</p>
            <button id="registerBtn" class="btn btn-primary">Register</button>
        </div>

        <form id="registrationForm" class="hidden" action="/checkMobileNumExist" method="post">
            <h2>Enter Mobile Number</h2>
            <div class="form-group">
                <input type="text" id="mobileNumberInput" placeholder="Mobile Number" name="mobileNumber"
                    class="form-input" required pattern="[0-9]{10}"
                    title="Please enter a valid 10-digit Indian mobile number">
            </div>
            <button type="submit" id="proceedBtn" class="btn btn-primary">Proceed</button>
        </form>
    </div>

    <!-- <script src="app.js"></script> -->
    <script>     
        const welcomeMessage = document.getElementById('welcomeMessage');
        const registrationForm = document.getElementById('registrationForm');
        const registerBtn = document.getElementById('registerBtn');
        const mobileNumberInput = document.getElementById('mobileNumberInput');

        registerBtn.addEventListener('click', () => {
            welcomeMessage.classList.add('hidden');
            registrationForm.classList.remove('hidden');
        });

        mobileNumberInput.addEventListener('input', () => {
            const mobileNumber = mobileNumberInput.value;
            const pattern = /^[0-9]{10}$/;
            if (!pattern.test(mobileNumber)) {
                mobileNumberInput.setCustomValidity('Please enter a valid 10-digit Indian mobile number');
            } else {
                mobileNumberInput.setCustomValidity('');
            }
        });
    </script>
</body>

</html>