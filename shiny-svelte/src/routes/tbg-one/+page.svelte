<script lang="ts">
	import { pathState } from '$lib/path.svelte.js';

	class Gold {
		readonly value: number;

		constructor(value: number) {
			this.value = value;
		}
	}

	//type SemaphoreColor = 'red' | 'yellow' | 'green';

	class SemaphoreColorString {
		readonly value: string;

		constructor(value: string) {
			this.value = value;
		}
	}

	class BetResult {
		readonly color: string;
		readonly win: boolean;
		readonly gold: number;

		constructor(color: string, win: boolean, gold: number) {
			this.color = color;
			this.win = win;
			this.gold = gold;
		}
	}

	//const notEnoughGold = 'not-enough-gold';

	let gold = $state(new Gold(0));
	let semaphoreColorString = $state<SemaphoreColorString>(new SemaphoreColorString('yellow'));

	async function getGold() {
		const response = await fetch(pathState.backendBase);
		gold = await response.json();
	}
	async function getDoubleGold() {
		const response = await fetch(pathState.backendDoubleGold, { method: 'POST' });
		gold = await response.json();
	}
	async function getSemaphoreColor() {
		const response = await fetch(pathState.backendSemaphoreColor);
		semaphoreColorString = await response.json();
	}
	async function postSemaphoreColor() {
		const response = await fetch(pathState.backendSemaphoreColor, { method: 'POST' });
		semaphoreColorString = await response.json();
	}
	async function betRed() {
		const response = await fetch(pathState.backendBetRed, { method: 'POST' });
		const betResult: BetResult = await response.json();
		console.log(betResult);
		gold = new Gold(betResult.gold);
		semaphoreColorString = new SemaphoreColorString(betResult.color);
	}
	async function betGreen() {
		const response = await fetch(pathState.backendBetGreen, { method: 'POST' });
		const betResult: BetResult = await response.json();
		console.log(betResult);
		gold = new Gold(betResult.gold);
		semaphoreColorString = new SemaphoreColorString(betResult.color);
	}
	async function reset() {
		const response = await fetch(pathState.backendReset, { method: 'POST' });
		await getGold();
		semaphoreColorString = new SemaphoreColorString('yellow');
	}
	async function loseBet() {
		const response = await fetch(pathState.backendLoseBet, { method: 'POST' });
		await getGold();
		semaphoreColorString = new SemaphoreColorString('yellow');
	}
	async function refreshUi() {
		await getGold();
	}
</script>

<p>Turn Based Game: One (3)</p>
<p>
	<button onclick={reset}>Reset</button>
</p>
<button onclick={getGold}>Get gold</button>
<button onclick={getDoubleGold}>Get double gold</button>
<p>
	Gold: {gold.value}
</p>
<button onclick={postSemaphoreColor}>Change semaphore color</button>
<p>
	Semaphore color: {semaphoreColorString.value}
</p>
<button onclick={betRed}>Bet 100: Red</button>
<button onclick={betGreen}>Bet 100: Green</button>
<button onclick={loseBet}>Lose 50</button>
