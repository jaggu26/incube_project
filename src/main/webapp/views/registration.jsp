<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Form</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f2f2f2;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 82vh;
            position: absolute;
            top: 18%;
            right: 33%;
        }

        .container {
            background-color: #fff;
            border-radius: 29px;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 400px;
            padding-right: 46px;
        }

        .container h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            font-weight: bold;
            display: block;
            margin-bottom: 1px;
        }

        .form-group input,
        .form-group select {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .form-group input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            cursor: pointer;
        }

        .form-group input[type="submit"]:hover {
            background-color: #0056b3;
        }

        .password-container {
            position: relative;
        }

        .password-container input {
            padding-right: 8px;
            /* Make room for the icon */
        }

        .password-container i {
            position: absolute;
            top: 50%;
            right: 10px;
            transform: translateY(-50%);
            cursor: pointer;
        }
    </style>
</head>

<body>
    <div class="container">
        <h2>Registration Form</h2>
        <form action="/submitRegister" method="post">
            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" id="name" name="name" value="${user.name}" ${not empty user.name ? 'disabled' : '' }
                    required>
            </div>
            <div class="form-group">
                <label for="name">User Name</label>
                <input type="text" id="username" name="username" value="${user.userName}" ${not empty user.name
                    ? 'disabled' : '' } required maxlength="8" oninput="checkUsernameLength(this)">
            </div>
            <div class="form-group">
                <label for="areaOfInterest">Area of Interest</label>
                <select id="areaOfInterest" name="areaOfInterest" onchange="handleAreaOfInterestChange(this.value)"
                    required>
                    <!-- Bcakend Cource list are coming -->
                </select>
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" value="${user.email}" ${not empty user.email ? 'disabled'
                    : '' } required>
            </div>
            <div class="form-group">
                <label for="mobileNum">Mobile Number</label>
                <input type="tel" id="mobileNum" name="mobileNum" value="${user.mobileNum}" pattern="[0-9]{10}" ${not
                    empty user.mobileNum ? 'disabled' : '' } required>
            </div>
            <div class="form-group">
                <label for="whatsAppNum">WhatsApp Number</label>
                <input type="tel" id="whatsAppNum" name="whatsAppNum" value="${user.whatsAppNum}" pattern="[0-9]{10}"
                    ${not empty user.whatsAppNum ? 'disabled' : '' } required>
            </div>

            <div class="form-group">
                <label for="password">Password</label>
                <div class="password-container">
                    <input type="password" id="password" name="password" required>
                    <i class="fa fa-eye-slash" id="togglePassword"></i>
                </div>
            </div>

            <div class="form-group">
                <label for="message">Message</label>
                <textarea id="message" name="message" rows="4"></textarea>
            </div>
            <div class="form-group">
                <input type="submit" value="Submit">
            </div>
        </form>
    </div>

    <script src="../js/script.js"></script>
</body>

</html>