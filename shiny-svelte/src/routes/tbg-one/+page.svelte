<script>
	import { pathState } from '$lib/path.svelte.js';

	let gold = $state();

	async function getGold() {
		const response = await fetch(pathState.backendBase);
		gold = await response.json();
	}
</script>

<p>Turn Based Game: One (2)</p>
<button onclick={getGold}>Get gold</button>
