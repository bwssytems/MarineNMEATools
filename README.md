# MarineNMEATools
Tools for handling nmea messages that need conversion or correction

This project contains classes to handle converting NMEA sentences. One instance is th Sitex Autopilot which outputs a $APHDM message but does not contain a checksum. This is invalid for most software and will not be read.
