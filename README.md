# General
Simple API for retrieving information about the CyberPower UPS

# Prerequisites
 - Allow executing pwrstat with sudo NOPASSWD right
 - Install flask packages

```
echo "%sudo ALL=(ALL) NOPASSWD: /usr/sbin/pwrstat" | sudo tee /etc/sudoers.d/pwrstat
sudo apt install python3-flask python3-flask-restful
```

# Installation
```
git clone 'https://github.com/antonvovk/cyberpower-ups-api.git'
cd cyberpower-ups-api
sudo ./install.sh
```

# Uninstall
```
sudo ./uninstall.sh
```
