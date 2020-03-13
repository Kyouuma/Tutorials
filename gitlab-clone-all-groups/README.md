## Enable Creds Helper Globally:

> $ git config credential.helper store

you can switch to running ssh or use Git credential helper. When credentials storage is enabled, 
the first time you pull or push from the remote Git repository, you will be asked for a username and password, 
and they will be saved in ~/.git-credentials file.
Note: Your password will be stored unencrypted. 

## Environment: 

Gitlab CE: "version : 11.7.3", "api version: v4"
