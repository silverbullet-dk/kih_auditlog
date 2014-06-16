KIH Auditlog Plug-in
====================
[Grails](http://grails.org/) plug-in providing audit logging capabilities.

How to build and install
------------------------
Make sure you have JDK 1.6 and Grails 2.1 installed and in your path.

Package the plug-in by running

    grails package-plugin

This generates a ZIP file. In projects depending on the KIH Auditlog plug-in, install the plug-in by running

    grails install-plugin <path to the generated ZIP file>

from the command line.

Please note
----------------------
This repository is maintained exclusively by Silverbullet. The official version maintained by 4S can be found [here](https://bitbucket.org/4s/kih_auditlog).