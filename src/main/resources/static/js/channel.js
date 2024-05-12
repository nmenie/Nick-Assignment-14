async function getChannel(channel) {
    let responseEntity = await fetch('/channel', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(channel)
    })
