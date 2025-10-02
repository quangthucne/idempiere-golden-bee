/* GoldenBee Role Panel - Split Screen Layout */
.goldenbee-role-main {
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: 100vh;
    background-color: #fbcb1b;
    padding: 20px;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

.goldenbee-role-card-container {
    width: 100%;
    max-width: 1000px;
}

.goldenbee-role-card {
    display: flex;
    background: #ffffff;
    border-radius: 20px;
    box-shadow: 0 15px 40px rgba(0, 0, 0, 0.15);
    overflow: hidden;
    border: 3px solid #000000;
    min-height: 600px;
}

/* Left Section */
.goldenbee-role-left-section {
    flex: 1;
    padding: 50px 40px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    text-align: center;
    color: white;
}

.goldenbee-role-left-logo {
    max-height: 250px;
    margin-bottom: 30px;
}

.goldenbee-role-welcome-title {
    font-size: 28px;
    font-weight: 700;
    margin-bottom: 15px;
    line-height: 1.3;
}

.goldenbee-role-welcome-msg {
    font-size: 16px;
    opacity: 0.9;
    line-height: 1.5;
}

/* Right Section */
.goldenbee-role-right-section {
    flex: 1;
    padding: 50px 40px;
    display: flex;
    align-items: center;
    justify-content: center;
}

.goldenbee-role-form-container {
    width: 100%;
    max-width: 400px;
}

/* Form Fields */
.goldenbee-role-field-container {
    margin-bottom: 20px;
}

.goldenbee-role-field-label {
    display: block;
    font-size: 12px;
    font-weight: 400;
    color: #000000;
    margin-bottom: 8px;
    padding-left: 8px;
    letter-spacing: 0.5px;
    text-align: start;
}

.goldenbee-role-input-container {
    width: 100%;
}

.goldenbee-role-select,
.goldenbee-role-date {
    width: 100%;
    padding: 12px 15px;
    border: 2px solid #e0e0e0;
    border-radius: 8px;
    font-size: 14px;
    background: #ffffff;
    color: #000000;
    box-sizing: border-box;
}

.goldenbee-role-select:focus,
.goldenbee-role-date:focus {
    outline: none;
    border-color: #fbcb1b;
    box-shadow: 0 0 0 3px rgba(251, 203, 27, 0.2);
}

.goldenbee-role-select:hover,
.goldenbee-role-date:hover {
    border-color: #cccccc;
}

/* Default Link */
.goldenbee-role-default-container {
    text-align: center;
    margin: 20px 0;
}

.goldenbee-role-default-link {
    color: #fbcb1b;
    font-size: 14px;
    font-weight: 600;
    text-decoration: underline;
    cursor: pointer;
    display: inline-block;
    padding: 5px 10px;
}

.goldenbee-role-default-link:hover {
    color: #000000;
}

/* Buttons */
.goldenbee-role-button-container {
    display: flex;
    justify-content: center;
    gap: 12px;
    margin-top: 30px;
    padding-top: 20px;
    border-top: 1px solid #f0f0f0;
}

.goldenbee-role-btn {
    padding: 12px 25px;
    border-radius: 8px;
    font-size: 14px;
    font-weight: 600;
    border: 2px solid transparent;
    cursor: pointer;
    min-width: 80px;
    letter-spacing: 0.5px;
}

.goldenbee-role-btn.primary {
    background: #fbcb1b;
    color: #ffffff;
    border-color: #fbcb1b;
}

.goldenbee-role-btn.primary:hover {
    background: #fbca1be5;
    color: #ffffff;
    box-shadow: 0 4px 12px rgba(251, 203, 27, 0.3);
}

.goldenbee-role-btn.secondary {
    background: #ffffff;
    color: #000000;
    border-color: #000000;
}

.goldenbee-role-btn.secondary:hover {
    background: #000000;
    color: #ffffff;
}

/* Responsive Design */
@media (max-width: 768px) {
    .goldenbee-role-card {
        flex-direction: column;
        min-height: auto;
    }
    
    .goldenbee-role-left-section {
        padding: 30px 25px;
        min-height: 200px;
    }
    
    .goldenbee-role-right-section {
        padding: 30px 25px;
    }
    
    .goldenbee-role-welcome-title {
        font-size: 24px;
    }
    
    .goldenbee-role-welcome-msg {
        font-size: 14px;
    }
}

@media (max-width: 480px) {
    .goldenbee-role-main {
        padding: 10px;
    }
    
    .goldenbee-role-card {
        border-radius: 15px;
    }
    
    .goldenbee-role-left-section,
    .goldenbee-role-right-section {
        padding: 25px 20px;
    }
    
    .goldenbee-role-button-container {
        flex-direction: column;
    }
    
    .goldenbee-role-btn {
        width: 100%;
        justify-content: center;
    }
}

.goldenbee-role-card {
}