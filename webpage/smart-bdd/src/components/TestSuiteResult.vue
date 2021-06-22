<template>
  <div>
    <h5>{{methodName }}: {{ status }}</h5>
    <p>{{ wordify }}</p>
  </div>
</template>

<script lang="ts">
export default {
  name: "TestSuiteResult",
  props: ['wordify', 'status', 'methodName', 'className', 'packageName'],
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
