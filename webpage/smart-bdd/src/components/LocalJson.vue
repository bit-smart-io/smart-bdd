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
