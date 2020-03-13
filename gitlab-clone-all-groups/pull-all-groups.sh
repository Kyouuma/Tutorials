#!/bin/bash

# When credentials storage is enabled, 
# the first time you pull or push from the remote Git repository,
# you will be asked for a username and password, 
# and they will be saved in ~/.git-credentials file.

# Enabling Git credential helper:
# $ git config credential.helper store

# To get an access token from Gitlab https://docs.gitlab.com/ee/user/profile/personal_access_tokens.html
# change the access token and the gitlab server's url.
# you're good to go --->


for group in $(curl "https://gitlab.<example.com>/api/v4/groups?private_token=<>" | jq .[].path | tr -d '"'); do

mkdir $group;

    for repo in $(curl "https://gitlab.<example.com>/api/v4/groups/$group?private_token=<>" | jq .projects[].http_url_to_repo | tr -d '"'); do 

    cd $group;
    git clone $repo; 
    cd .. ;
    done;

done;
