#!/bin/bash

CURRENT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
PROJECT_ROOT=$CURRENT_DIR/..
PRODUCT_DIR=$PROJECT_ROOT/.out/prod

if [ ! -d "$PRODUCT_DIR" ]; then
    echo "ERROR: The product directory does not exist! $PRODUCT_DIR"
else
    electron-packager $PRODUCT_DIR --out=$PROJECT_ROOT/.dist --icon=$PROJECT_ROOT/clkwrk.icns --overwrite
fi
