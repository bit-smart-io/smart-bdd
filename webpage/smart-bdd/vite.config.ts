import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  base: './'
})


// //https://cli.vuejs.org/guide/html-and-static-assets.html#disable-index-generation
// build: {
//   extend (config, { isDev, isClient }) {
//     if (!isDev) {
//       // relative links, please.
//       config.output.publicPath = './_nuxt/'
//     }
//     return config;
//   }
// }

// module.exports = {
//     publicPath: process.env.NODE_ENV === 'production'
//         ? '/my-project/'
//         : '/'
// }