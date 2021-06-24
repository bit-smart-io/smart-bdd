<template>
  <div>
<!--    <h4>Load embedded index - Raw Json</h4>-->
<!--    <p> {{ jsonIndex }} </p>-->

    <h4>Summary Of All Tests</h4>
    <p> {{ jsonIndex.summary }} </p>

    <h4>All Tests Suites - Please select one</h4>
    <ul class="no-bullets">
      <li v-for="link in jsonIndex.links.testSuites" v-on:click="loadJson(link.file)">
        {{ link.name }}
      </li>
    </ul>

    <br />

<!--    <h4>Test Suite Results - Raw Json</h4>-->
<!--    <div id="test-suite-result">-->
<!--      {{ testSuiteResultsJson }}-->
<!--    </div>-->

    <ul class="no-bullets">
      <li v-for="testCase in testSuiteResultsJson.testCases">
        <test-suite-result
          :wordify="testCase.wordify"
          :status="testCase.status"
          :methodName="testCase.methodName"
          :className="testCase.className"
          :packageName="testCase.packageName" >
        </test-suite-result>
      </li>
    </ul>

  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import TestSuiteResult from "./TestSuiteResult.vue";

declare interface TestSuit {
  name: string;
  className: string;
  packageName: string;
  methodNames: string[];
  summary: Summary;
  testCases: TestCase[];
}

declare interface Summary {
    passed: number;
    skipped: number;
    failed: number;
    aborted: number;
    tests: number;
}

declare interface TestCase {
  wordify: string;
  status: string;
  methodName: string;
  className: string;
  packageName: string;
}

export default defineComponent({
  name: "EmbeddedJson",
  components: { TestSuiteResult },
  data() {
    return {
      testSuiteResultsJson: {
        name: '',
        className: '',
        packageName: '',
        methodNames: [''],
        summary: {
          passed: 0,
          skipped: 0,
          failed: 0,
          aborted: 0,
          tests: 0
        },
        testCases: [{
          wordify: '',
          status: '',
          methodName: '',
          className: '',
          packageName: ''
        }],
      }
    }
  },
  computed: {
    /**
     * TODO this will be:
     * 1. elementById
     * 2. http for either local or remote http
     **/
    jsonIndex() {
      const element = document.getElementById('json-index')
      if (element) {
        return JSON.parse(element.innerHTML);
      }
      return "";
    },
  },
  methods: {
    /**
     * TODO this will be:
     * 1. elementById
     * 2. http for either local or remote http
     **/
    loadJson(file: string) {
      const element = document.getElementById(file);
      if (element) {
        this.testSuiteResultsJson = <TestSuit>JSON.parse(element.innerHTML);
      }
    },
  }
});
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
