# Git Branching and Merging Hands-on (Git-HOL 3)

## Objectives
- Explain branching and merging.
- Explain creating a branch request in GitLab.
- Explain creating a merge request in GitLab.
- Create a branch, make changes, and merge with trunk (`main`/`master`).

## Prerequisites
- Git installed and configured.
- P4Merge configured as difftool (optional but recommended for visual diff).
- A GitHub/GitLab account for remote operations.

Estimated time: **30 minutes**

## Branching
Run these commands from this folder:

```bash
git checkout -b GitNewBranch
git branch -a
```

- `*` in branch output marks the current branch.

Create sample files and commit:

```bash
echo "This file was added in GitNewBranch for branching lab." > lab_notes_1.txt
echo "Second sample file in GitNewBranch to demonstrate merge." > lab_notes_2.txt
git add lab_notes_1.txt lab_notes_2.txt
git commit -m "Add sample files in GitNewBranch"
git status
```

## Merging
Switch to trunk (`master` if present, otherwise `main`):

```bash
git checkout main
```

Show command-line differences:

```bash
git diff --stat main GitNewBranch
git diff main GitNewBranch
```

Show visual differences with P4Merge:

```bash
git difftool --tool=p4merge --no-prompt main GitNewBranch
```

Merge branch into trunk:

```bash
git merge GitNewBranch
```

Observe merge log graph:

```bash
git log --oneline --graph --decorate
```

Delete merged branch and verify:

```bash
git branch -d GitNewBranch
git status
git branch -a
```

## GitLab: Branch Request (Practical)
GitLab typically uses branch creation + push as the practical "branch request":

```bash
git checkout -b GitNewBranch
git push -u origin GitNewBranch
```

Then share the branch name in task/issue comments if team workflow requires approval before MR.

## GitLab: Merge Request
1. Push branch commits to remote.
2. Open project in GitLab.
3. Go to **Merge requests** -> **New merge request**.
4. Set source branch = `GitNewBranch`.
5. Set target branch = `main` (or `master`).
6. Add title/description/reviewer and create MR.
7. Merge after approvals and successful pipeline.
8. Optionally delete source branch.

## Notes From This Environment
- Default trunk branch is `main`.
- `p4merge` was not found in PATH during this run, so visual diff did not open.
- A successful merge can be fast-forward if trunk has no divergent commits.
