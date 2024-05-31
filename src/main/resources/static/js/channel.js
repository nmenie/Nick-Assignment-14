async function getChannel(channelId) {
  try {
    let response = await fetch(`/channels/${channelId}`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json'
      }
    });

    if (response.ok) {
      let channel = await response.json();

      if (channel) {
        // Channel exists, perform desired actions
        console.log('Channel exists:', channel);
        // Redirect to the general channel page
        window.location.href = `/channels/${channelId}`;
      } else {
        // Channel does not exist, handle accordingly
        console.log('Channel does not exist');
        // Display an error message or take appropriate action
        alert('Channel not exist');
      }
    } else {
      // Handle error response
      console.log('Error checking channel existence:', response.status);
      // Display an error message or take appropriate action
      alert('Error checking channel existence');
    }
  } catch (error) {
    // Handle network or other errors
    console.log('Error:', error);
    // Display an error message or take appropriate action
    alert('An error occurred');
  }
}
