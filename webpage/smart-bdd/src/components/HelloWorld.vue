<template>
  <div>

    <p> {{ indexSummary }} </p>
    <ul class="no-bullets">
      <li v-for="link in indexLinks.testSuites">
        {{ link.name }}
      </li>
    </ul>
<!--    <ul>-->
<!--      <li v-for="file in indexLinkFiles">-->
<!--        {{ file }}-->
<!--      </li>-->
<!--    </ul>-->
  </div>
</template>

<script lang="ts">
import index from "../assets/sample-data/index.json";
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
    }
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
