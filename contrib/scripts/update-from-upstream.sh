#!/bin/sh
# Update all branches from the upstream repository
#
# Usage: ./update-from-upstream.sh
#
# SPDX-FileCopyrightText: 2022 Carles Fernandez-Prades <cfernandez(at)cttc.es>
# SPDX-License-Identifier: MIT

version="1.0"
branches="master langdale kirkstone honister hardknott gatesgarth dunfell zeus warrior thud sumo rocko"

if ! [ -x "$(command -v git)" ]; then
    echo "Please install git before using this script."
    exit 1
fi

display_usage() {
    echo "update-from-upstream.sh v$version - This script pulls all branches from the upstream repo."
    echo " Supported branches: $branches"   
    echo " Usage:"
    echo "  ./update-from-upstream.sh"
}

if [ "$1" = "--help" ] || [ "$1" = "-h" ]; then
    display_usage
    exit 0
fi

if [ "$1" = "--version" ] || [ "$1" = "-v" ]; then
    echo "update-from-upstream.sh v$version"
    exit 0
fi

upstream="https://github.com/carlesfernandez/meta-gnss-sdr"
currentbranch=$(git rev-parse --abbrev-ref HEAD 2> /dev/null)

if [ -z "$currentbranch" ]; then
    echo "We are not in a git repository. Exiting."
    exit 1
fi

echo "Running update-from-upstream.sh v$version ..."

git fetch $upstream

for branch in $branches; do
    git checkout "$branch"
    git pull $upstream "$branch"
done

git checkout "$currentbranch"
echo "update-from-upstream.sh v$version executed successfully."