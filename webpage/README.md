This is the prototype for the interactive web app. Doing the bare minimum to prove the concept.

Vue 3 was chosen over React as Vue should be more simple.

helper-scripts:
* `init.sh` first use and or if you update the webapp run this to copy any changes to webapp in the temp dir
* `run.sh` starts/runs the webapp in the temp dir
* `copy-reports` copies from data in the temp dir to the webapp in the temp dir

For now the usage is, run `run.sh` and then `copy-reports` to update the webapp with the last results.

Prerequisites you need `npm` installed.

Please note I have not started the static html yet.

Recreation steps for Vue.js 3 with Vite. 

```shell
npm install vue@next
npm install -g @vue/cli
➜  smart-bdd git:(master) ✗ npm init @vitejs/app smart-bdd
Need to install the following packages:
@vitejs/create-app
Ok to proceed? (y) y
✔ Select a framework: · vue
✔ Select a variant: · TypeScript

Done. Now run:

  cd smart-bdd
  npm install
  npm run dev
  
  prod 
  npm run build
```

If we want axios do this
```
npm install axios
npm install --save axios
```