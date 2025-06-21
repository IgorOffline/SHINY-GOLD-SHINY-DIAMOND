<script>
	let gold = $state();

	async function getGold() {
		const response = await fetch('http://localhost:8080/');
		gold = await response.json();
	}
</script>

<p>Turn Based Game: One</p>
<button onclick={getGold}>Get gold</button>
