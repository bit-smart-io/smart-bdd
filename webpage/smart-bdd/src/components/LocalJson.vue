<!--
  - Smart BDD - The smart way to do behavior-driven development.
  - Copyright (C)  2021  James Bayliss
  -
  - This program is free software: you can redistribute it and/or modify
  - it under the terms of the GNU General Public License as published by
  - the Free Software Foundation, either version 3 of the License, or
  - (at your option) any later version.
  -
  - This program is distributed in the hope that it will be useful,
  - but WITHOUT ANY WARRANTY; without even the implied warranty of
  - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  - GNU General Public License for more details.
  -
  - You should have received a copy of the GNU General Public License
  - along with this program.  If not, see <https://www.gnu.org/licenses/>.
  -->

<template>
  <div>
    <h4>Summary Of All Tests Example</h4>
    <p> {{ indexSummary }} </p>

    <h4>All Tests Suites Example</h4>
    <ul class="no-bullets">
      <li v-for="link in indexLinks.testSuites" @click="showResults(link.file)">
        {{ link.name }}
      </li>
    </ul>

    <h4>ClassUnderTest Example</h4>
    <ul class="no-bullets">
      <li v-for="testCase in classUnderTest.testCases">
        <b>{{ testCase.methodName }}</b>: {{ testCase.wordify }}
      </li>
    </ul>

    <h4>ClassUnderTest Full JSON</h4>
    <p> {{ classUnderTest }} </p>
  </div>
</template>

<script lang="ts">
import index from "../assets/sample-data/index.json";
import classUnderTest from "../assets/sample-data/TEST-shared.undertest.ClassUnderTest.json";
//import axios from "axios"

export default {
  name: "HelloWorld",
  computed: {
    // example of having a list of test suite names
    indexLinkNames() {
      return index.links.testSuites.map((item) => {
        return item.name;
      })
    },
    // example of having a list of test suite files
    indexLinkFiles() {
      return index.links.testSuites.map((item) => {
        return item.file;
      })
    },
    // TODO marshall to an actual object
    indexLinks() {
      return index.links;
    },
    indexSummary() {
      return index.summary;
    },
    classUnderTest() {
      return classUnderTest;
    },
  },
  methods: {
    showResults(file: string) {
      // can't load a local file using file://
      // const fileRelative : string =  "../assets/sample-data/" + file;
      // const fileRelative : string =  "./" + file;
      //
      // // // import index from "../assets/sample-data/index.json";
      // fetch(fileRelative)
      // // //axios.get(fileRelative)
      //     .then(function (response) {
      //       // handle success
      //       console.log(response);
      //     })
      //     .catch(function (error) {
      //       // handle error
      //       console.log(error);
      //     })
      //     .then(function () {
      //       // always executed
      //     });
      return console.log("............file: " + file);
    },
  }
};
</script>

<style scoped>
a {
  color: #42b983;
}

label {
  margin: 0 0.5em;
  font-weight: bold;
}

code {
  background-color: #eee;
  padding: 2px 4px;
  border-radius: 4px;
  color: #304455;
}

ul.no-bullets {
  list-style-type: none; /* Remove bullets */
  padding: 0; /* Remove padding */
  margin: 0; /* Remove margins */
}
</style>
