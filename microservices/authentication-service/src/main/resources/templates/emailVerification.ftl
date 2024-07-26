<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Account Verification</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 100%;
            max-width: 600px;
            margin: 0 auto;
            background-color: #ffffff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .header {
            text-align: center;
            padding: 10px 0;
            background-color: #007bff;
            color: #ffffff;
        }
        .content {
            padding: 20px;
            line-height: 1.6;
            text-align: justify;
        }
        .btn {
            display: inline-block;
            background-color: #007bff;
            color: #ffffff;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 5px;
        }
        .footer {
            text-align: center;
            padding: 10px 0;
            color: #888888;
            font-size: 12px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="header">
        <h1>Account Verification</h1>
    </div>
    <div class="content">
        <p>Dear ${userName},</p>
        <p>Your cosmo market admin account has been created. To complete your registration, please verify your email address by clicking the button below:</p>
        <p style="text-align: center;">
            <a href="${verificationLink}" class="btn">Verify Account</a>
        </p>
        <p>If the button above does not work, please copy and paste the following link into your browser:</p>
        <p><a href="${verificationLink}">${verificationLink}</a></p>
        <p>This verification link will expire in ${expirationTime} hours.</p>
        <p>If you did not create an account with us, please ignore this email.</p>
        <p>Best regards,<br/>Cosmotech International Pvt. Ltd.</p>
    </div>
    <div class="footer">
        <p>&copy; ${currentYear} Cosmotech International Pvt. Ltd. All rights reserved.</p>
    </div>
</div>
</body>
</html>
