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
<!--    <h4>Load embedded index - Raw Json</h4>-->
<!--    <p> {{ indexJson }} </p>-->

    <h4>Summary Of All Tests</h4>
    <p> {{ indexJson.summary }} </p>

    <h4>All Tests Suites - Please select one</h4>
    <ul class="no-bullets">
      <li v-for="link in indexJson.links.testSuites" v-on:click="loadTestSuite(link.file)">
        {{ link.name }}
      </li>
    </ul>

    <br />

<!--    <h4>Test Suite Results - Raw Json</h4>-->
<!--    <div id="test-suite-result">-->
<!--      {{ testSuiteResultsJson }}-->
<!--    </div>-->

    <ul class="no-bullets">
      <h4 v-if="testSuiteResultsJson.name"> {{ testSuiteResultsJson.name }} </h4>
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
import { defineComponent } from 'vue';
import TestSuiteResult from "./TestSuiteResult.vue";
// import axios from 'axios'

// TODO is this worth pursuing?
// import { Component, Prop, Vue } from 'vue-property-decorator'

/** TestSuite result / TEST-shared.undertest.ClassUnderTest.json */
declare interface TestSuite {
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

/** ReportIndex / index.json */
declare interface ReportIndex {
  links: TestSuiteLinks;
  summary: Summary;
}

declare interface TestSuiteLinks {
  testSuites: TestSuiteLink[];
}

declare interface TestSuiteLink {
  name: string;
  file: string;
}

export default defineComponent({
  name: "LoadJson",
  components: { TestSuiteResult },
  async created() {
    await this.loadIndex()
    // console.log("............created called this.indexJson: " + JSON.stringify(this.indexJson));
  },
  data() {
    return {
      indexJson: {
        links: {
          testSuites: [
            {
              name: "",
              file: ""
            }
          ]
        },
        summary: {
          passed: 4,
          skipped: 4,
          failed: 8,
          aborted: 4,
          tests: 18
        }
      } as ReportIndex,
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
      } as TestSuite
    }
  },
  computed: {
    // example of having a list of test suite names
    indexLinkNames(): string[] {
        return this.indexJson.links.testSuites.map((item) => {
          return item.name;
        })
    },
  },
  methods: {
    async loadIndex() {
      const response: Response = await fetch("index.json");
      if (response.ok) {
        this.indexJson = await response.json()
        // console.log("............loadIndex this.indexJson: " + JSON.stringify(this.indexJson));
      }
    },
    async loadTestSuite(file: string) {
      const response: Response = await fetch(file);
      if (response.ok) {
        this.testSuiteResultsJson = await response.json()
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
