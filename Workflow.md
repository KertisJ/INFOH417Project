# INFOH417Project
-# Initializing your local environment

1. Clone the repo

`git clone https://github.com/KertisJ/INFOH417Project.git`

2. Create a local branch for local development
 Suppose i create a branch that i want to work 
 on and give it my name ... or rather "dev"

`git branch dev`

` git checkout dev`

3. Set upstream branch as your local master

`git branch --set-upstream-to main dev`

# Working on the project

1. Pull changes to be up-to-date

`git checkout main`

`git pull`

`git checkout dev`

`git pull`

2. Work on the project by modifying code...

3. Add changes, verify and commit

`git add .`

`git status`

`git commit -m "Commit message"`


4. Checkout your local master branch and merge branches

`git checkout main`

`git pull`

`git merge dev`

5. Push your changes

`git push`

