/* Golden Bee Login Panel Styles */
.goldenbee-login-container {
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: 100vh;
    padding: 20px;
    background: #fbcb1b;
    font-family: 'Segoe UI', Arial, sans-serif;
}

.goldenbee-login-card {
    display: flex;
    max-width: 900px;
    width: 100%;
    background: white;
    border-radius: 16px;
    box-shadow: 0 15px 40px rgba(0,0,0,0.15);
    overflow: hidden;
    min-height: 550px;
    border: 2px solid #000000;
}

/* Left Section - Branding */
.goldenbee-left-section {
    flex: 1;
    background-color: #ffffff;
    padding: 50px 40px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    text-align: center;
    color: #000000;
}

.goldenbee-logo-container {
    margin-bottom: 30px;
}

.goldenbee-logo {
    max-height: 200px;
    max-width: 100%;
}

.goldenbee-welcome-title {
    font-size: 28px;
    font-weight: 700;
    margin-bottom: 10px;
    color: #000000;
}

.goldenbee-welcome-subtitle {
    font-size: 16px;
    color: #333;
    opacity: 0.9;
}

/* Right Section - Form */
.goldenbee-right-section {
    flex: 1;
    padding: 50px 40px;
    display: flex;
    flex-direction: column;
    justify-content: center;
}

.goldenbee-form-header {
    margin-bottom: 30px;
    text-align: center;
}

.goldenbee-form-title {
    font-size: 1.5em;
    font-weight: 600;
    color: #000000;
    margin-bottom: 5px;
}

.goldenbee-form-container {
    width: 100%;
}

/* Form Fields */
.goldenbee-field-container {
    margin-bottom: 25px;
}

.goldenbee-field-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 8px;
}

.goldenbee-field-label {
    font-weight: 600;
    color: #000000;
    font-size: 14px;
    display: block;
}

.goldenbee-input-container {
    position: relative;
}

.goldenbee-input-field {
    width: 100%;
    padding: 14px 16px 14px 40px;
    border: 2px solid #e1e5e9;
    border-radius: 8px;
    font-size: 14px;
    background: #ffffff;
    color: #000000;
    box-sizing: border-box;
}

.goldenbee-input-field:focus {
    outline: none;
    border-color: #fbcb1b;
    box-shadow: 0 0 0 3px rgba(251, 203, 27, 0.1);
}

/* Icons for input fields */
.goldenbee-input-container.user-icon::before {
    content: '👤';
    position: absolute;
    left: 12px;
    top: 50%;
    transform: translateY(-50%);
    z-index: 1;
}

.goldenbee-input-container.password-icon::before {
    content: '🔒';
    position: absolute;
    left: 12px;
    top: 50%;
    transform: translateY(-50%);
    z-index: 1;
}

.goldenbee-input-container.language-icon::before {
    content: '🌐';
    position: absolute;
    left: 12px;
    top: 50%;
    transform: translateY(-50%);
    z-index: 1;
}

/* Options Container */
.goldenbee-options-container {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin: 20px 0 30px;
}

.goldenbee-checkboxes-container {
    display: flex;
    flex-direction: column;
    gap: 8px;
}

.goldenbee-checkbox {
    margin: 2px 0;
    color: #000000;
}

.goldenbee-reset-link {
    color: #fbcb1b;
    text-decoration: none;
    font-weight: 500;
    font-size: 14px;
    transition: color 0.3s;
}

.goldenbee-reset-link:hover {
    color: #fbcb1c;
}

/* Buttons */
.goldenbee-buttons-container {
    margin-top: 10px;
}

.goldenbee-login-button {
    padding: 20px 30px;
    border-radius: 8px;
    font-weight: 600;
    font-size: 14px;
    border: none;
    cursor: pointer;
    transition: all 0.3s ease;
    margin: 0 5px;
    min-width: 120px;
}

.goldenbee-login-button.primary {
    background: #fbcb1b;
    color: #000000;
}

.goldenbee-login-button.primary:hover {
    background: #fbcb1c;
    transform: translateY(-2px);
}

.goldenbee-login-button.secondary {
    background: #6c757d;
    color: white;
}

.goldenbee-login-button.secondary:hover {
    background: #5a6268;
    transform: translateY(-2px);
}

/* SSO Integration */
.goldenbee-sso-container {
    margin-top: 30px;
}

.goldenbee-sso-separator {
    height: 1px;
    background: #e1e5e9;
    margin: 25px 0;
    position: relative;
    text-align: center;
}

.goldenbee-sso-separator-text {
    background: white;
    padding: 0 15px;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    color: #666;
    font-size: 12px;
}

.goldenbee-sso-buttons {
    display: flex;
    flex-direction: column;
    gap: 12px;
}

.goldenbee-sso-button {
    background: #ffffff;
    border: 2px solid #e1e5e9;
    color: #000000;
    padding: 12px;
    border-radius: 8px;
    font-weight: 500;
    transition: all 0.3s ease;
    cursor: pointer;
}

.goldenbee-sso-button:hover {
    border-color: #fbcb1b;
    background: #fffbf0;
}

/* Responsive Design */
@media (max-width: 768px) {
    .goldenbee-login-card {
        flex-direction: column;
    }
    
    .goldenbee-left-section {
        padding: 30px 20px;
    }
    
    .goldenbee-right-section {
        padding: 30px 20px;
    }
    
    .goldenbee-options-container {
        flex-direction: column;
        gap: 15px;
        align-items: flex-start;
    }
    
    .goldenbee-welcome-title {
        font-size: 24px;
    }
    
    .goldenbee-form-title {
        font-size: 20px;
    }
}

@media (max-width: 480px) {
    .goldenbee-login-container {
        padding: 10px;
    }
    
    .goldenbee-left-section,
    .goldenbee-right-section {
        padding: 20px 15px;
    }
    
    .goldenbee-login-button {
        padding: 12px 20px;
        min-width: 100px;
        font-size: 13px;
    }
}