Consider this example of
playing a film with subtitles:

- `Playing a film with no stubtitles`
- `Playing a film without stubtitles`
- `Playing a film with English stubtitles`

Above are reasonable usages of English and are easy to understandable. The one you choose is led from the first scenario
write. What happens when you want to update audio?

- `Playing a film with no stubtitles and Dolby Atmos audio`
- `Playing a film with no stubtitles and Dolby Digital audio`
- `Playing a film with no stubtitles and Dolby DTS-HD Master Audio audio`

The last step leaves you a problem. Your regular expression to capture the audio is hanging on a knife edge. Our options
are now:

- `Playing a film with no stubtitles and 'Dolby DTS-HD Master Audio' audio`
- `Playing a film with 'no' stubtitles and 'Dolby DTS-HD Master Audio' audio`
- `Playing a film with stubtitles set to non and audio set to Dolby DTS-HD Master Audio`
- `Playing a film with stubtitles set to 'non' and audio set to 'Dolby DTS-HD Master Audio'`

We now have quite few options and possibly a lot to refactor if we want to make all the tests consistent i.e., using
same notation to set state. What if want to add playback seed? Do keep adding to the same step? 

Thus far we have been hiding the underlying abstraction that is playback settings.