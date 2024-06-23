# General
Simple API for retrieving information about the CyberPower UPS

# Prerequisites
 - Allow executing pwrstat with sudo NOPASSWD right

```
echo "%sudo ALL=(ALL) NOPASSWD: /usr/sbin/pwrstat" | sudo tee /etc/sudoers.d/pwrstat
```
