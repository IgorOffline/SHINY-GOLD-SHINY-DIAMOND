<script lang="ts">
	let { children } = $props();
</script>

<nav>
	<a href="/">home</a>
	<a href="/tbg-one">tbg-one</a>
	<a href="/tbg-two">tbg-two</a>
	<a href="/tbg-three">tbg-three</a>
</nav>

{@render children()}
