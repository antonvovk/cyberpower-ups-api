# General
Simple API for retrieving information about the CyberPower UPS

# Prerequisites
 - Allow executing pwrstat with sudo NOPASSWD right

```
echo "%sudo ALL=(ALL) NOPASSWD: /usr/sbin/pwrstat" | sudo tee /etc/sudoers.d/pwrstat
```

# Build
Create native executable for linux:
```
curl -s "https://get.sdkman.io" | bash
sdk install java 23.1.3.r21-nik
sdk user java 23.1.3.r21-nik
mvn -Pnative clean native:compile
```
