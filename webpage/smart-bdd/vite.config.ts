/*
 * Smart BDD - The smart way to do behavior-driven development.
 * Copyright (C)  2021  James Bayliss
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

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