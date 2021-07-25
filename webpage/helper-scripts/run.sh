#!/usr/bin/env bash

cd $TMPDIR/io.bitsmart.bdd.report/smart-bdd/

# dev
npm run dev

# prod - this is too slow for now and doesn't allow hot reloading of data in public
#npm run build
#npm install -g serve
#serve -s dist
