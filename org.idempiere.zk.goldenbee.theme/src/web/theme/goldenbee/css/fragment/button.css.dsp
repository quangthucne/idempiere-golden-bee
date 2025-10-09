<%-- customization of buttons --%>
.z-button, .z-button-os {
  display: inline-flex;
  align-items: center;
  padding: 10px 20px;
  justify-content: center;
  cursor: pointer;
  gap: 5px;
}
.z-button-os {
	border-radius: 4px;
}
.z-button-os:hover, .z-button-os:active {
    color: #FFFFFF;
    border-color: transparent;
    background-color: #fbcb1b;
}

.z-button:focus {
    border-color: black;
}

.z-button.btn-small {
	padding: 1px 5px;
}
.z-button.btn-medium {
	padding: 2px 10px;
}

.img-btn img {
	height: 22px;
	width: 22px;
	background-color: transparent;
}

.txt-btn img, .small-img-btn img, .img-txt-btn img {
	height: 16px;
	width: 16px;
	background-color: transparent;
	vertical-align: middle;
	display: inline-block;
}

.btn-sorttab {
	box-shadow: 0px 0px 4px #bbb;
	color: #555;
	border: solid 1px #bbb;
	text-shadow: 0px 1px 2px #888;
}

.btn-cancel, .btn-help {
    background: #f1f1f1;
    color: #111111;
}

.btn-cancel:hover, .btn-help:hover {
    background: #e5e5e5;
    color: #1d1d1d;
}

.z-icon-Help, .z-icon-Cancel {
    color: #111111 !important;
}

.z-button [class^="z-icon-"][disabled],
.z-button-os [class^="z-icon-"][disabled]{
	font-size: larger;
	color: #333;	
	padding-left: 2px;
	padding-right: 2px;
}
.z-button.xlarge-toolbarbutton [class^="z-icon-"] {
	font-size: 24px;
}
.z-button.large-toolbarbutton [class^="z-icon-"] {
	font-size: 20px;
}
.z-button.medium-toolbarbutton [class^="z-icon-"] {
	font-size: 16px;
}
.z-button.small-toolbarbutton [class^="z-icon-"] {
	font-size: 12px;
}
.btn-ok.z-button [class^="z-icon-"]:before {
	color: white;	
}

.btn-new, .btn-ok, .btn-small {
	background: #fbcb1b !important;
}

.btn-reset {
	background: #111111 !important;
}

.z-combobox-button, .z-bandbox-button, .z-datebox-button, .z-timebox-button,
 .z-spinner-button, .z-doublespinner-button {
	vertical-align: top;
}

.z-combobox-button {
	position: static !important;
    top: unset !important;
    left: unset !important;
    transform: unset !important;
	padding-left: 4px !important;
	border-radius: 5px;
	background-color: #fbcb1b;
}

.z-bandbox-button [class*="z-icon-"], .z-combobox-button [class*="z-icon-"] {
	font-size: 16px;
}

.mobile .login-btn {
    font-size: 0px !important;
}