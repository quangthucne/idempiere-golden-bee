/* GoldenBee Role Panel - Compact Card Design */
.goldenbee-role-main {
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: 100vh;
    background: linear-gradient(135deg, #fbcb1b 0%, #fbcb1b 100%);
    padding: 20px;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

.goldenbee-role-card-container {
    width: 100%;
    max-width: 500px;
}

.goldenbee-role-card {
    background: #ffffff;
    border-radius: 16px;
    box-shadow: 0 10px 30px rgba(0, 0, 0, 0.15);
    padding: 30px;
    border: 2px solid #000000;
}

/* Header */
.goldenbee-role-header {
    text-align: center;
    margin-bottom: 20px;
    padding-bottom: 20px;
    border-bottom: 2px solid #fbcb1b;
}

.goldenbee-role-logo {
    max-height: 100px;
    margin-bottom: 15px;
}

.goldenbee-role-title {
    font-size: 24px;
    font-weight: 700;
    color: #000000;
    margin: 0;
}

/* Welcome Message */
.goldenbee-role-welcome {
    text-align: center;
    margin-bottom: 25px;
}

.goldenbee-role-welcome-message {
    font-size: 14px;
    color: #666;
    line-height: 1.4;
    margin: 0;
}

/* Form Grid */
.goldenbee-role-form-grid {
    display: grid;
    grid-template-columns: 1fr 1fr;
    gap: 20px;
    margin-bottom: 20px;
}

.goldenbee-role-form-col {
    display: flex;
    flex-direction: column;
    gap: 15px;
}

/* Form Fields */
.goldenbee-role-field-container {
    display: flex;
    flex-direction: column;
    gap: 5px;
}

.goldenbee-role-label {
    font-size: 12px;
    font-weight: 600;
    color: #000000;
    text-transform: uppercase;
    letter-spacing: 0.5px;
}

.goldenbee-role-input-container {
    width: 100%;
}

.goldenbee-role-select,
.goldenbee-role-date {
    width: 100%;
    padding: 10px 12px;
    border: 2px solid #e0e0e0;
    border-radius: 8px;
    font-size: 13px;
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
    margin: 15px 0;
}

.goldenbee-role-default-link {
    color: #fbcb1b;
    font-size: 13px;
    font-weight: 600;
    text-decoration: underline;
    cursor: pointer;
    display: inline-block;
    padding: 5px;
    transition: color 0.3s ease;
}

.goldenbee-role-default-link:hover {
    color: #fbcb1b;
}

/* Buttons */
.goldenbee-role-button-container {
    display: flex;
    justify-content: center;
    gap: 10px;
    margin-top: 25px;
    padding-top: 20px;
    border-top: 1px solid #f0f0f0;
}

.goldenbee-role-btn {
    padding: 10px 25px;
    border-radius: 8px;
    font-size: 13px;
    font-weight: 600;
    border: 2px solid transparent;
    cursor: pointer;
    transition: all 0.3s ease;
    min-width: 80px;
    text-transform: uppercase;
    letter-spacing: 0.5px;
}

.goldenbee-role-btn.primary {
    background: #fbcb1b;
    color: #000000;
    border-color: #fbcb1b;
}

.goldenbee-role-btn.primary:hover {
    background: #fbcb1c;
    color: #000000;
    transform: translateY(-2px);
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
    transform: translateY(-2px);
}

.z-comboitem-selected {
    background-color: #fbcb1b !important;
    color: #000000 !important;
}

/* Responsive Design */
@media (max-width: 600px) {
    .goldenbee-role-main {
        padding: 10px;
    }
    
    .goldenbee-role-card {
        padding: 25px 20px;
        border-radius: 12px;
    }
    
    .goldenbee-role-form-grid {
        grid-template-columns: 1fr;
        gap: 15px;
    }
    
    .goldenbee-role-title {
        font-size: 20px;
    }
    
    .goldenbee-role-button-container {
        flex-direction: column;
        gap: 10px;
    }
    
    .goldenbee-role-btn {
        width: 100%;
        justify-content: center;
    }
}

@media (max-width: 400px) {
    .goldenbee-role-card {
        padding: 20px 15px;
    }
    
    .goldenbee-role-title {
        font-size: 18px;
    }
    
    .goldenbee-role-welcome-message {
        font-size: 13px;
    }
}

/* Animation */
@keyframes cardAppear {
    from {
        opacity: 0;
        transform: scale(0.9) translateY(20px);
    }
    to {
        opacity: 1;
        transform: scale(1) translateY(0);
    }
}

.goldenbee-role-card {
    animation: cardAppear 0.5s ease-out;
}

/* Focus states for accessibility */
.goldenbee-role-select:focus-visible,
.goldenbee-role-date:focus-visible,
.goldenbee-role-btn:focus-visible {
    outline: 2px solid #fbcb1b;
    outline-offset: 2px;
}

/* Scrollbar styling for select elements */
.goldenbee-role-select::-webkit-scrollbar {
    width: 8px;
}

.goldenbee-role-select::-webkit-scrollbar-track {
    background: #f1f1f1;
    border-radius: 4px;
}

.goldenbee-role-select::-webkit-scrollbar-thumb {
    background: #fbcb1b;
    border-radius: 4px;
}

.goldenbee-role-select::-webkit-scrollbar-thumb:hover {
    background: #000000;
}