package com.bresan.glass.examples.VoiceInputExample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;

import com.google.android.glass.app.Card;

import java.util.List;

public class VoiceInputActivity extends Activity {

	private final static int CODE_SPEECH = 0;
	private final static String PROMPT_TEXT = "Voice Input Test. Say something!";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.layout_main);
		getVoiceInput();
	}

	public void getVoiceInput() {
		Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		intent.putExtra(RecognizerIntent.EXTRA_PROMPT, PROMPT_TEXT);
		startActivityForResult(intent, CODE_SPEECH);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == CODE_SPEECH && resultCode == RESULT_OK) {
			
			//get text from voice recognizer
			List<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
			String spokenText = results.get(0);

			// Add the text to the view so the user knows we retrieved it correctly
			Card card = new Card(this);
			card.setText(spokenText);
			View cardView = card.getView();
			setContentView(cardView);
		}

		super.onActivityResult(requestCode, resultCode, data);
	}
}
