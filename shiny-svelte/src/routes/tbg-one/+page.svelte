<script lang="ts">
	import { pathState } from '$lib/path.svelte.js';

	class Gold {
		readonly value: number;

		constructor(value: number) {
			this.value = value;
		}
	}

	let gold = $state(new Gold(0));

	async function getGold() {
		const response = await fetch(pathState.backendBase);
		gold = await response.json();
	}
	async function getDoubleGold() {
		const response = await fetch(pathState.backendDoubleGold, { method: 'POST' });
		gold = await response.json();
	}
</script>

<p>Turn Based Game: One (2)</p>
<button onclick={getGold}>Get gold</button>
<button onclick={getDoubleGold}>Get double gold</button>
<p>
	Gold: {gold.value}
</p>
